package com.ng.ngleetcode.test.协程启动框架

import android.util.Log
import com.ng.ngleetcode.app.AppScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

fun main() {

	println("start")
	runBlocking {
		val result = withTimeoutOrNull(2000) {
			println("1")
			GlobalScope.launch {
				println("getQimei start")
				delay(6000)
				println("getQimei end")

			}

			println("2")
			delay(1000)

			doSomething()

			println("3")
		}
		if (result == null) {
			println("Operation timed out")
		} else {
			println("Operation success")
		}
	}

	println("end")

	Thread.sleep(200000)
}

suspend fun doSomething() {
	println("doSomething start")

	Thread(kotlinx.coroutines.Runnable {

		Thread.sleep(3000)
		println("doSomething end")
	}).start()
//	return suspendCancellableCoroutine {
//		Thread(kotlinx.coroutines.Runnable {
//			Thread.sleep(3000)
//			println("what")
//			it.resumeWith(Result.success(Unit))
//		}).start()
//	}
}

fun log(msg: String) {
	Log.d("nangua-TaskManager", msg)
}

// 任务收集与注册(考虑用KSP+注解实现)
class LaunchTaskProxy : TaskProxy {
	override fun getAttachBaseContextTaskList(): List<BaseTask> {
		return listOf(AppInitTask())
	}

	override fun getAppOnCreateTaskList(): List<BaseTask> {
		return listOf(ImageLoadTask(), QimeiTask(), LogTask())

	}

	override fun getActivityCreateTaskList(): List<BaseTask> {
		return mutableListOf()
	}

	override fun getIdleTaskList(): List<BaseTask> {
		return mutableListOf()
	}

	//是否同意隐私协议
	private fun isAgreePrivacy(): Boolean = true

}

// 任务举例
/**
 * App静态变量初始化
 */
class AppInitTask : BaseTask() {
	override suspend fun execute(scope: CoroutineScope) {
		log("AppInitTask execute start")
		delay(50)
		log("AppInitTask execute end")
	}

	override val name: String = "AppInitTask"
	override var group: TaskInterval = TaskInterval.ApplicationOnCreate
}

/**
 * QIMEI sdk初始化，依赖AppInitTask
 */
class QimeiTask : BaseTask() {
	override suspend fun execute(scope: CoroutineScope) {
		return suspendCancellableCoroutine { it ->
			log("QimeiTask execute start")
			Thread {
				Thread.sleep(100)

				log("QimeiTask execute end")
				it.resumeWith(Result.success(Unit))
			}.start()
		}
	}

	override val name: String = "QimeiTask"
	override var group: TaskInterval = TaskInterval.ApplicationOnCreate
}

/**
 * 日志上报SDK初始化，依赖QimeiTask
 */
class LogTask : BaseTask() {
	override suspend fun execute(scope: CoroutineScope) {
		log("LogTask execute start")
		withContext(Dispatchers.IO) {
			Thread.sleep(50)
		}
		log("LogTask execute end")
	}

	override val name: String = "LogTask"
	override val depends: MutableSet<String> = mutableSetOf("QimeiTask")
	override var group: TaskInterval = TaskInterval.ApplicationOnCreate
}

class ImageLoadTask : IdleTask() {

	override suspend fun execute(scope: CoroutineScope) {
		log("ImageLoadTask execute start")
		withContext(Dispatchers.IO) {
			Thread.sleep(20)
		}
		log("ImageLoadTask execute end")
	}

	override val name: String = "ImageLoadTask"

}
