package com.ng.base.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/03/27
 * Time: 18:21
 */
object ColorUtil {

    fun getColor(context: Context?, color: Int): Int {
        context?.let {
            val defaultColor = ContextCompat.getColor(it, color)
            var colorTheme: Int by SPreference("color", defaultColor)
            return if (colorTheme != 0 && Color.alpha(colorTheme) != 255) {
                defaultColor
            } else {
                colorTheme
            }
        }
        return color
    }
}