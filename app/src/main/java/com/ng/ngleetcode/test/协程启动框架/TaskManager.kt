package com.ng.ngleetcode.test.协程启动框架

import android.os.Looper
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
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
enum class TaskInterval {

	/**
	 * Application attachBaseContext
	 */
	ApplicationAttachBase,

	/**
	 * Application创建
	 */
	ApplicationOnCreate,

	/**
	 * 主Activity创建
	 */
	ActivityOnCreate,

	/**
	 * 闲时执行的任务
	 */
	Idle,

}

/**
 * ## 任务定义
 */
interface ITask {

	/**
	 * 任务名称 && 耗时监控标识符
	 */
	val name: String

	/**
	 * 所属任务阶段名称（一般自动划分，不需要特殊指定）
	 */
	var group: TaskInterval
		get() = TaskInterval.ApplicationAttachBase
		set(_) {}

}

/**
 * ## 启动任务基类
 * 任务只会执行一次，不可重复初始化。
 */
abstract class BaseTask : DagNode(), ITask {

	/**
	 * 保证任务只执行一次
	 */
	private val isExecuted = AtomicBoolean(false)

	abstract suspend fun execute(scope: CoroutineScope)

	suspend fun tryExecute(scope: CoroutineScope) :Boolean{
		if (isExecuted.compareAndSet(false, true)) {
			execute(scope)
			return true
		}
		return false
	}

	override fun hashCode(): Int {
		return name.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return other is ITask && other.name == name
	}

}

/**
 * 闲时任务，执行时机不确定，不允许有依赖关系
 */
abstract class IdleTask : BaseTask() {

	override val depends: MutableSet<String> = mutableSetOf()

	override var group: TaskInterval = TaskInterval.Idle

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
object LaunchTaskManager {

	// 任务策略
	private var taskProxy: TaskProxy? = null

	// 任务执行
	private val taskExecutor = TaskExecutor() { task, cost ->
		taskMonitor.onTaskComplete(task, cost)
	}

	// 任务监控
	private val taskMonitor = TaskMonitor()

	fun init(launchTaskProxy: TaskProxy) {
		taskProxy = launchTaskProxy
	}

	fun onAttachBaseContext(scope: CoroutineScope) {
		launchTaskLog.i { "「onAttachBaseContext」 start" }
		taskExecutor.start(scope, taskProxy?.getAttachBaseContextTaskList())
		launchTaskLog.i { "「onAttachBaseContext」 end" }
	}

	fun onAppCreate(scope: CoroutineScope) {
		launchTaskLog.i { "「onAppCreate」 start" }
		taskExecutor.start(scope, taskProxy?.getAppOnCreateTaskList())
		launchTaskLog.i { "「onAppCreate」 end" }
	}

	fun onActivityCreate(scope: CoroutineScope) {
		launchTaskLog.i { "「onActivityCreate」 start" }
		taskExecutor.start(scope, taskProxy?.getActivityCreateTaskList())
		launchTaskLog.i { "「onActivityCreate」 end" }
	}

	fun addTaskListener(listener: TaskListener) {
		taskMonitor.addTaskListener(listener)
	}

}

/**
 * ## 任务收集与注册
 */
interface TaskProxy {

	/**
	 * 获取App#attachBaseContext阶段的任务列表
	 */
	fun getAttachBaseContextTaskList(): List<BaseTask>

	/**
	 * 获取App#OnCreate阶段的任务列表
	 */
	fun getAppOnCreateTaskList(): List<BaseTask>

	/**
	 * 获取主Activity#OnCreate阶段的任务列表
	 */
	fun getActivityCreateTaskList(): List<BaseTask>

	/**
	 * 获取闲时任务列表
	 */
	fun getIdleTaskList(): List<BaseTask>

}

/**
 * ## 任务执行
 * todo jzn 让业务可以策略选择Scheduler
 * 分为同步任务和异步任务
 * 同步任务：任务在主线程执行
 * 异步任务：任务在非主线程执行（协程实现）
 */
class TaskExecutor(
	onTaskComplete: ((BaseTask, Long) -> Unit)?,
) {

	private val normalScheduler = NormalScheduler(onTaskComplete)

	private val idleScheduler = IdleScheduler(onTaskComplete)

	fun start(scope: CoroutineScope, taskList: List<BaseTask>?) {
		taskList?.let { list ->
			// 普通任务
			normalScheduler.start(scope, list.filter { it.group != TaskInterval.Idle })
			// 闲时任务
			idleScheduler.start(scope, list.filter { it.group == TaskInterval.Idle })
		}
	}

}


/**
 * ## 闲时任务执行器
 */
class IdleScheduler(private val onTaskComplete: ((BaseTask, Long) -> Unit)?) {

	fun start(scope: CoroutineScope, taskList: List<BaseTask>) {
		taskList.forEach { task ->
			addIdleTask(scope, task)
			task.children.forEach {
				addIdleTask(scope, it as BaseTask)
			}
		}
	}

	private fun addIdleTask(scope: CoroutineScope, task: BaseTask) {
		Looper.myQueue().addIdleHandler {
			scope.launch {
				val result: Result<Boolean>
				val cost = measureTimeMillis {
					result = kotlin.runCatching {
						task.tryExecute(scope)
					}.onFailure {
						launchTaskLog.i {
							"executing idle task [${task.name}] error " + it
						}
					}
				}
				if (result.isSuccess && result.getOrNull() == true) {
					onTaskComplete?.invoke(task, cost)
				}
				launchTaskLog.i {
					"Execute idle task [${task.name}] complete " +
							"thread [${Thread.currentThread().name}], " +
							"cost: ${cost}ms, result: $result"
				}
			}
			false
		}
	}
}


/**
 * ## 普通任务执行器
 */
class NormalScheduler(private val onTaskComplete: ((BaseTask, Long) -> Unit)?) {

	fun start(scope: CoroutineScope, taskList: List<BaseTask>) {
		//检查是否符合Dag结构
		DagUtils.checkDag(taskList)
		scope.launch {
			// 执行无依赖的任务
			taskList.filter { it.depends.isEmpty() }.forEach { task ->
				execute(scope, task)
			}
		}
	}

	private suspend fun execute(scope: CoroutineScope, task: BaseTask) {
		launchTaskLog.i {
			"Execute task [${task.name}] start " + "thread [${Thread.currentThread().name}]"
		}
		val result: Result<Boolean>
		val cost = measureTimeMillis {
			result = kotlin.runCatching {
				task.tryExecute(scope)
			}.onFailure {
				launchTaskLog.i { "executing task [${task.name}] error " + it }
			}
		}
		if (result.isSuccess && result.getOrNull() == true) {
			// 只统计执行成功的任务
			onTaskComplete?.invoke(task, cost)
		}
		launchTaskLog.i {
			"Execute task [${task.name}] complete " + "thread [${Thread.currentThread().name}], " +
					"cost: ${cost}ms, result: $result"
		}
		task.children.map { it as BaseTask }.forEach {
			execute(scope, it)
		}
	}

}

/**
 * ## 任务监控
 *
 * todo jzn 参考TimeCostUtil
 * 1.对启动耗时span做划分，各个大的阶段，内部拆分小的阶段
 * 2.只统计登录成功，且同意隐私协议下的用户耗时。
 *
 * 上报时机：Activity#onCreate结束后。
 * 闲时执行的任务不做上报。
 */
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

