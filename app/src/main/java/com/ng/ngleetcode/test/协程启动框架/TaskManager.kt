package com.ng.ngleetcode.test.协程启动框架

import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

/**
 * 有向无环，Node 节点
 */
abstract class DagNode {

	/**
	 * 节点唯一标识符
	 */
	abstract val name: String

	/**
	 * 所依赖的name（如果没有依赖可以不实现）
	 */
	open val depends: MutableSet<String> = mutableSetOf()

	/**
	 * 依赖该节点的子节点集合
	 * 业务无需关心，用来组成节点依赖关系。
	 */
	val children: MutableSet<DagNode> = mutableSetOf()

	override fun hashCode(): Int {
		return name.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return other is DagNode && other.name == name
	}

	override fun toString(): String {
		return name
	}

}

object DagUtils {

	// 生成并检查是否符合Dag结构
	fun checkDag(nodeList: List<DagNode>)  {
		val nodeMap = nodeList.associateBy { it.name }

		nodeList.forEach { node ->
			checkCircularDependency(listOf(node.name), node.depends, nodeMap)
			node.depends.forEach { depend ->
				val foundDepend = nodeMap[depend]
				checkNotNull(foundDepend) {
					"Can not find task [$depend] which depend by task [${node.name}]"
				}
				foundDepend.children.add(node)
			}
		}
	}

	/**
	 * 检查有向无环图是否有环
	 */
	private fun checkCircularDependency(
		chain: List<String>,
		depends: Set<String>,
		taskMap: Map<String, DagNode>
	) {
		depends.forEach { depend ->
			if (chain.contains(depend)) {
				throw IllegalArgumentException("Found circular dependency chain: $chain -> $depend")
			} else {
				taskMap[depend]?.let { task ->
					println("Checking dependency: $chain -> $depend")
					checkCircularDependency(chain + depend, task.depends, taskMap)
				}
			}
		}
	}

	fun printDAG(root: DagNode, indent: String = "") {
		println(indent + root.name)
		for (child in root.children) {
			printDAG(child, "-$indent  ")
		}
	}
}

/**
 * ## 任务阶段定义
 * （比如t0，t1，t2）
 */
data class TaskStage(
	val stageName: String, //阶段名
	val stageTaskList: MutableList<BaseTask> //阶段任务列表
)
/**
 * ## 任务定义
 */
interface ITask {

	/**
	 * 任务名称 && 耗时监控标识符
	 */
	val name: String

}

/**
 * ## 启动任务基类
 * 任务只会执行一次，不可重复初始化。
 */
abstract class BaseTask : DagNode(), ITask {

	/**
	 * 任务所属场景名
	 */
	var sceneName: String? = null

	/**
	 * 任务所属阶段名
	 */
	var stageName: String? = null

	/**
	 * 记录未完成的依赖数量
	 */
	val remainingDependencies = AtomicInteger()

	override fun hashCode(): Int {
		return name.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return other is ITask && other.name == name
	}

}

/**
 *  异步任务
 */
abstract class AsyncTask : BaseTask() {

	abstract suspend fun execute(scope: CoroutineScope)

}

/**
 * 同步任务
 */
abstract class SyncTask : BaseTask() {

	abstract fun execute()

}

/**
 * 闲时任务，执行时机不确定，不允许有依赖关系
 */
abstract class IdleTask : SyncTask() {

	override val depends: MutableSet<String> = mutableSetOf()

}

/**
 * ## 启动任务管理器
 * 可在任意场景下使用，对启动任务进行管理
 *
 * ### 功能特性
 * 1. 按 DAG 结构检查和执行任务
 * 2. 支持任务同/异步执行
 * 3. 全流程监控
 */
class TaskManager(
	scene: String, // 场景
	taskListener: MutableList<TaskListener>
) {

	// 任务监控
	private val taskMonitor = TaskMonitor(scene, taskListener)

	// 任务执行
	private val taskExecutor = TaskExecutor(taskMonitor)

	fun onStage(scope: CoroutineScope, taskStage: TaskStage) {
		taskMonitor.onStageStart(taskStage)
		val cost = measureTimeMillis {
			taskExecutor.trySchedule(scope, taskStage.stageTaskList)
		}
		taskMonitor.onStageEnd(taskStage, cost)
	}
}

/**
 * ## 任务执行
 * 分为同步任务和异步任务
 * 同步任务：任务在主线程执行
 * 异步任务：任务在非主线程执行（协程实现）
 */
class TaskExecutor(
	private val taskMonitor: TaskMonitor
) {

	fun trySchedule(scope: CoroutineScope, taskList: List<BaseTask>?) {
		taskList?.let { list ->
			// 检查是否符合Dag结构
			DagUtils.checkDag(list)
			// 初始化每个任务的未完成依赖数量
			list.forEach { task ->
				task.remainingDependencies.set(task.depends.size)
			}
			// 调度无依赖的任务（根节点任务）
			schedule(scope, list.filter { it.depends.isEmpty() })
		}
	}

	private fun schedule(scope: CoroutineScope, taskList: List<BaseTask>) {
		// 执行无依赖的任务（根节点任务）
		taskList.forEach { task ->
			when (task) {
				is IdleTask -> {
					addIdleTask(scope, task)
				}
				is AsyncTask -> {
					scope.launch {
						executeAsync(scope, task)
					}
				}
				is SyncTask -> {
					executeSync(scope, task)
				}
			}
		}
	}

	private fun addIdleTask(scope: CoroutineScope, task: IdleTask) {
		Looper.myQueue().addIdleHandler {
			executeSync(scope, task)
			false
		}
	}

	private fun executeSync(scope: CoroutineScope, task: SyncTask) {
		taskMonitor.onTaskStart(task)
		var error: Throwable? = null
		val cost = measureTimeMillis {
			kotlin.runCatching {
				task.execute()
			}.onFailure {
				error = it
				launchTaskLog.i { "executing task [${task.name}] error " + it }
			}
		}
		taskMonitor.onTaskEnd(task, cost, error)
		// 任务完成，通知依赖该任务的子任务
		taskCompleted(scope, task)
	}

	private suspend fun executeAsync(scope: CoroutineScope, task: AsyncTask) {
		taskMonitor.onTaskStart(task)
		var error: Throwable? = null
		val cost = measureTimeMillis {
			kotlin.runCatching {
				task.execute(scope)
			}.onFailure {
				error = it
				launchTaskLog.i { "executing task [${task.name}] error " + it }
			}
		}
		taskMonitor.onTaskEnd(task, cost, error)
		// 任务完成，通知依赖该任务的子任务
		taskCompleted(scope, task)
	}

	private fun taskCompleted(scope: CoroutineScope, completedTask: BaseTask) {
		completedTask.children.forEach { childTask ->
			// 减少子任务的未完成依赖数量
			val remaining = (childTask as BaseTask).remainingDependencies.decrementAndGet()
			if (remaining == 0) {
				// 所有依赖任务都完成，执行该任务
				when (childTask) {
					is IdleTask -> {
						addIdleTask(scope, childTask)
					}
					is AsyncTask -> {
						scope.launch {
							executeAsync(scope, childTask)
						}
					}
					is SyncTask -> {
						executeSync(scope, childTask)
					}
				}
			}
		}
	}
}

/**
 * ## 任务监控
 */
class TaskMonitor(
	private val scene: String,
	private val taskListenerList: List<TaskListener>
) {

	fun onTaskStart(task: BaseTask) {
		launchTaskLog.i {
			"Execute task [${task.name}] start " +
					"thread [${Thread.currentThread().name}]"
		}
		taskListenerList.forEach { it.onTaskStart(task) }
	}

	fun onTaskEnd(task: BaseTask, cost: Long, error: Throwable?) {
		launchTaskLog.i {
			"Execute task [${task.name}] complete " +
					"thread [${Thread.currentThread().name}], " +
					"cost: ${cost}ms, error: $error"
		}
		taskListenerList.forEach { it.onTaskEnd(task, cost, error) }
	}

	fun onStageStart(stage: TaskStage) {
		launchTaskLog.i { "[" + scene + "][" + stage.stageName + "] start" }
		taskListenerList.forEach { it.onStageStart(stage) }
	}

	fun onStageEnd(stage: TaskStage, cost: Long) {
		launchTaskLog.i { "[" + scene + "][" + stage.stageName + "] end" }
		taskListenerList.forEach { it.onStageEnd(stage, cost) }
	}


}

interface TaskListener {
	fun onTaskStart(task: BaseTask)

	fun onTaskEnd(task: BaseTask, cost: Long, error: Throwable?)

	fun onStageStart(stage: TaskStage)

	fun onStageEnd(stage: TaskStage, cost: Long)

}

class BuglyReport : TaskListener {

	override fun onTaskStart(task: BaseTask) {
		launchTaskLog.i {
			"BuglyReport task [${task.name}] start "
		}
	}

	override fun onTaskEnd(task: BaseTask, cost: Long, error: Throwable?) {
		launchTaskLog.i {
			"BuglyReport task [${task.name}] complete " +
					"cost: $cost" +
					"error: $error"
		}
	}

	override fun onStageStart(stage: TaskStage) {
		launchTaskLog.i {
			"BuglyReport task [${stage.stageName}] stage start "
		}
	}

	override fun onStageEnd(stage: TaskStage, cost: Long) {
		launchTaskLog.i {
			"BuglyReport task [${stage.stageName}] stage complete " +
					"cost: $cost"
		}
	}

}

