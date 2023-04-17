package com.ng.base

import android.app.Application
import android.content.Context

/**
 * @date 2020/5/9
 * 
 */
open class BaseApp :Application() {


    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }

    companion object{
        private lateinit var baseApplication:BaseApp

        fun getContext(): Context {
            return baseApplication
        }
    }
}