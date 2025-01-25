package com.ng.ngleetcode.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.ng.base.utils.SPreference
import com.ng.ngleetcode.app.http.store.DataStoreUtils
import com.ng.ngleetcode.test.协程启动框架.AppInitTask
import com.ng.ngleetcode.test.协程启动框架.BuglyReport
import com.ng.ngleetcode.test.协程启动框架.ImageLoadTask
import com.ng.ngleetcode.test.协程启动框架.LogTask
import com.ng.ngleetcode.test.协程启动框架.QimeiTask
import com.ng.ngleetcode.test.协程启动框架.TaskManager
import com.ng.ngleetcode.test.协程启动框架.TaskStage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

object AppScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main.immediate + SupervisorJob()
}
class MyApp : Application() {

    val launchTaskManager = TaskManager("AppLaunch", mutableListOf(BuglyReport()))

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: MyApp

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        launchTaskManager.onStage(
            AppScope,
            TaskStage("App#attachBaseContext", mutableListOf(AppInitTask()))
        )
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CONTEXT = this

        SPreference.setContext(this)
        DataStoreUtils.init(this)

        launchTaskManager.onStage(
            AppScope,
            TaskStage("App#onCreate", mutableListOf(ImageLoadTask(), QimeiTask(), LogTask()))
        )
        //TaskManager.onAppCreate(AppScope)
    }
}