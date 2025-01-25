package com.ng.ngleetcode.test.协程启动框架

import android.util.Log


val launchTaskLog = Logger("HYLaunchTaskManager")

@JvmInline
value class Logger(val tag: String) {

	inline fun i(throwable: Throwable? = null, msg: () -> String) {
		Log.i(tag, msg(), throwable)
	}
}