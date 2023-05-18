package com.ng.base.utils

import android.util.Log

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/19
 */
object MLog {

    private val TAG = "nangua"

    fun d(str: String?) {
        str?.let {
            Log.d(TAG, str)
        }
    }

    fun printLine(tag: String, isTop: Boolean) {
        if (isTop) {
            Log.d(
                tag,
                "╔═══════════════════════════════════════════════════════════════════════════════════════"
            )
        } else {
            Log.d(
                tag,
                "╚═══════════════════════════════════════════════════════════════════════════════════════"
            )
        }
    }
}