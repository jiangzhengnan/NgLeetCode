package com.ng.ngleetcode.test.协程启动框架

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CopyOnWriteArrayList

interface OnAccountLoginListener {
	open fun onLoginSuccess(userInfo: String)
}

object AccountManager {
	private val loginListeners = CopyOnWriteArrayList<OnAccountLoginListener>()
	fun addLoginCallback(callback: OnAccountLoginListener) {
		if (!loginListeners.contains(callback)) {
			loginListeners.add(callback)
		}
	}

	fun login() {
		loginListeners.forEach { it.onLoginSuccess("") }
	}

}

suspend fun execute(scope: CoroutineScope) {
	return suspendCancellableCoroutine {
		AccountManager.addLoginCallback(object : OnAccountLoginListener {
			override fun onLoginSuccess(userInfo: String) {
				println("onLoginSuccess")
				it.resumeWith(Result.success(Unit))
			}

		})
	}
}
fun main() {

	GlobalScope.launch {
		println("start")
		execute(this)
		println("end")
	}

	GlobalScope.launch {
		delay(2000)
		AccountManager.login()
	}

	Thread.sleep(20000)
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

// 任务举例
/**
 * App静态变量初始化
 */
class AppInitTask : SyncTask() {

	override fun execute() {
		log("AppInitTask execute start")
//		Thread.sleep(50)
		log("AppInitTask execute end")
	}

	override val name: String = "AppInitTask"
}

/**
 * QIMEI sdk初始化，依赖AppInitTask
 */
class QimeiTask : AsyncTask() {
	override suspend fun execute(scope: CoroutineScope) {
		return suspendCancellableCoroutine { it ->
			log("QimeiTask execute start")
			Thread {
//				Thread.sleep(100)
				log("QimeiTask execute end")
				it.resumeWith(Result.success(Unit))
			}.start()
		}
	}

	override val name: String = "QimeiTask"
}

/**
 * 日志上报SDK初始化，依赖QimeiTask
 */
class LogTask : SyncTask() {

	override fun execute() {
		log("LogTask execute start")
//		Thread.sleep(50)
		log("LogTask execute end")
	}

	override val name: String = "LogTask"
	override val depends: MutableSet<String> = mutableSetOf("QimeiTask", "ImageLoadTask")
}

class ImageLoadTask : SyncTask() {


	override fun execute() {
		log("ImageLoadTask execute start")
//		Thread.sleep(1000)

		log("ImageLoadTask execute end")
	}

	override val name: String = "ImageLoadTask"

}
