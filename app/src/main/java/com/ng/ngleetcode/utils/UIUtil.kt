package com.ng.ngleetcode.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import com.ng.ngleetcode.app.MyApp

/**
 * @ProjectName: DXZS
 * @Package: com.dx.dxzs.util
 * @Description:
 * @Author: Eden
 * @CreateDate: 2019/7/9 16:44
 */
object UIUtil {

    fun dp2px(context: Context?, dpValue: Float): Int {
        val metrics: DisplayMetrics = if (context != null) {
            context.resources.displayMetrics
        } else {
            Resources.getSystem().displayMetrics
        }
        return (dpValue * metrics.density + 0.5f).toInt()
    }
    fun getString(id: Int): String {
        return getResources().getString(id)
    }

    fun getColor(id: Int): Int {
        return getResources().getColor(id)
    }

    private fun getContext(): Context {
        return MyApp.instance
    }

    fun sp2px(context: Context?, spVal: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spVal, context?.resources?.displayMetrics
        )
    }
    /**
     * 获取资源对象
     */
    private fun getResources(): Resources {
        return getContext().resources
    }

}