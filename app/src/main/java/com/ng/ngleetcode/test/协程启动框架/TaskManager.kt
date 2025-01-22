package com.ng.ngleetcode.test.协程启动框架

import android.os.Looper
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

/**
 * 有向无环，Node 节点
 */
abstract class DagNode {
	abstract val name: String    // 节点唯一标识符
	abstract val depends: MutableSet<String> // 业务需要传入依赖的name

	// 业务无需关心，用来组成节点依赖关系。
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


//任务定义
interface ITask {
	/**
	 * 任务名称 && 耗时监控标识符
	 */
	val name: String

	/**
	 * 所属任务组名称
	 * App#OnCreate阶段为T1，Activity渲染阶段为T2
	 */
	val group: TaskGroup

	/**
	 * 所依赖的任务名称
	 */
	val depends: MutableSet<String>

	/**
	 * 任务优先级
	 */
	val priority: Int

}

abstract class BaseTask : DagNode(), ITask, Comparable<ITask> {
	abstract suspend fun execute()

	override fun hashCode(): Int {
		return name.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return other is ITask && other.name == name
	}

	override fun compareTo(other: ITask): Int {
		return priority.compareTo(other.priority)
	}
}

/**
 * 闲时任务，执行时机不确定，不允许有依赖关系
 */
abstract class IdleTask : BaseTask() {

	override val depends: MutableSet<String> = mutableSetOf()
	override val group: TaskGroup = TaskGroup.Idle
	override val priority: Int = 0
}

// 任务组定义，App#OnCreate阶段为T1，Activity渲染阶段为T2
enum class TaskGroup {
	ApplicationOnCreate,
	ActivityOnCreate,
	Idle,   //闲时执行的任务
	Foreground, //App回到前台执行的任务
	Background //App回到后台执行的任务
}

// 任务管理器
object TaskManager {
	// 任务收集与注册
	private val taskCollector = TaskCollector()

	// 任务执行
	private val taskExecutor = TaskExecutor() { task, cost ->
		taskMonitor.onTaskComplete(task, cost)
	}

	// 任务监听
	private val taskMonitor = TaskMonitor()

	fun onAppCreate(scope: CoroutineScope) {
		taskExecutor.start(scope, taskCollector.getAppOnCreateTaskList())
	}

	fun onActivityCreate(scope: CoroutineScope) {
		taskExecutor.start(scope, taskCollector.getActivityCreateTaskList())
	}

	fun addTaskListener(listener: TaskListener) {
		taskMonitor.addTaskListener(listener)
	}

}

// 任务执行
class TaskExecutor(
	onTaskComplete: ((BaseTask, Long) -> Unit)?,
) {
	private val normalScheduler = NormalScheduler(onTaskComplete)
	private val idleScheduler = IdleScheduler(onTaskComplete)

	fun start(scope: CoroutineScope, taskList: List<BaseTask>) {
		// 普通任务
		normalScheduler.start(scope, taskList.filter { it.group != TaskGroup.Idle })
		// 闲时任务
		idleScheduler.start(scope, taskList.filter { it.group == TaskGroup.Idle })
	}

}

// 闲时任务执行器
class IdleScheduler(private val onTaskComplete: ((BaseTask, Long) -> Unit)?) {

	fun start(scope: CoroutineScope, taskList: List<BaseTask>) {
		taskList.forEachIndexed { index, task ->
			addIdleTask(scope, task)
			task.children.forEach {
				addIdleTask(scope, it as BaseTask)
			}
		}
	}

	fun addIdleTask(scope: CoroutineScope, task: BaseTask) {
		Looper.myQueue().addIdleHandler {
			scope.launch {
				val result: Result<Unit>
				val cost = measureTimeMillis {
					result = kotlin.runCatching {
						task.execute()
					}.onFailure {
						log("executing idle task [${task.name}] error " + it)
					}
				}
				onTaskComplete?.invoke(task, cost)
				log(
					"Execute idle task [${task.name}] complete " + "thread [${
						Thread
							.currentThread().name
					}], " +
							"cost: ${cost}ms, result: $result"
				)
			}
			false
		}
	}
}


//普通任务执行器
class NormalScheduler(private val onTaskComplete: ((BaseTask, Long) -> Unit)?) {
	fun start(scope: CoroutineScope, taskList: List<BaseTask>) {
		//检查是否符合Dag结构
		DagUtils.checkDag(taskList)
		scope.launch {
			// 执行无依赖的任务
			taskList.sorted().filter { it.depends.isEmpty() }.forEach { task ->
				execute(task)
			}
		}
	}

	private suspend fun execute(task: BaseTask) {
		val result: Result<Unit>
		val cost = measureTimeMillis {
			result = kotlin.runCatching {
				task.execute()
			}.onFailure {
				log("executing task [${task.name}] error " + it)
			}
		}
		onTaskComplete?.invoke(task, cost)
		log(
			"Execute task [${task.name}] complete " + "thread [${Thread.currentThread().name}], " +
					"cost: ${cost}ms, result: $result"
		)
		task.children.map { it as BaseTask }.forEach {
			execute(it)
		}
	}

}

// 任务监听
class TaskMonitor {
	private val taskListenerList = mutableListOf<TaskListener>()
	fun addTaskListener(listener: TaskListener) {
		taskListenerList.add(listener)
	}

	fun onTaskComplete(task: BaseTask, cost: Long) {
		taskListenerList.forEach {
			it.onTaskComplete(task, cost)
		}
	}
}

interface TaskListener {
	fun onTaskComplete(task: BaseTask, cost: Long)
}


// 任务举例
/**
 * App静态变量初始化
 */
class AppInitTask : BaseTask() {
	override suspend fun execute() {
		log("AppInitTask execute start")
		log("AppInitTask execute end")
	}

	override val name: String = "AppInitTask"
	override val depends: MutableSet<String> = mutableSetOf()
	override val group: TaskGroup = TaskGroup.ApplicationOnCreate
	override val priority: Int = 1
}

/**
 * QIMEI sdk初始化，依赖AppInitTask
 */
class QimeiTask : BaseTask() {
	override suspend fun execute() {
		return suspendCancellableCoroutine{it->
			log("QimeiTask execute start")
			Thread {
				Thread.sleep(1000)

				log("QimeiTask execute end")
				it.resumeWith(Result.success(Unit))
			}.start()
		}
	}

	override val name: String = "QimeiTask"
	override val depends: MutableSet<String> = mutableSetOf("AppInitTask")
	override val group: TaskGroup = TaskGroup.ApplicationOnCreate
	override val priority: Int = 2
}

/**
 * 日志上报SDK初始化，依赖QimeiTask
 */
class LogTask : BaseTask() {
	override suspend fun execute() {
		log("LogTask execute start")
		withContext(Dispatchers.IO) {
			Thread.sleep(50)
		}
		log("LogTask execute end")
	}

	override val name: String = "LogTask"
	override val depends: MutableSet<String> = mutableSetOf("QimeiTask")
	override val group: TaskGroup = TaskGroup.ApplicationOnCreate
	override val priority: Int = 3
}

class ImageLoadTask : IdleTask() {
	override suspend fun execute() {
		log("ImageLoadTask execute start")
		withContext(Dispatchers.IO) {
			Thread.sleep(2000)
		}
		log("ImageLoadTask execute end")
	}

	override val name: String = "ImageLoadTask"

}

// 任务收集与注册(考虑用KSP+注解实现)
class TaskCollector {

	/**
	 * 获取App#OnCreate阶段的任务列表
	 */
	fun getAppOnCreateTaskList(): List<BaseTask> {
		return if (isAgreePrivacy()) {
			listOf(AppInitTask(), QimeiTask(), LogTask())
		} else {
			listOf(AppInitTask())
		}
	}

	fun getActivityCreateTaskList(): List<BaseTask> {
		return listOf(ImageLoadTask())
	}

	//是否同意隐私协议
	private fun isAgreePrivacy(): Boolean = true

}

fun log(msg: String) {
	Log.d("TaskManager", msg)
}
