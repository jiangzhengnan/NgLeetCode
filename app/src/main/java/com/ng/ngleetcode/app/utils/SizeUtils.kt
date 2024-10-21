package com.ng.ngleetcode.app.utils

import android.content.res.Resources

object SizeUtils {
    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}