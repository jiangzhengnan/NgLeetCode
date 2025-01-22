package com.ng.ngleetcode.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.ng.base.utils.SPreference
import com.ng.ngleetcode.app.http.store.DataStoreUtils
import com.ng.ngleetcode.test.协程启动框架.TaskManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

object AppScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main.immediate + SupervisorJob()
}
class MyApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: MyApp

    }

    override fun onCreate() {
        super.onCreate()
        //a
        instance = this
        CONTEXT = this
        SPreference.setContext(this)
        DataStoreUtils.init(this)


        TaskManager.onAppCreate(AppScope)
    }
}