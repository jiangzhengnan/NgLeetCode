package com.ng.ngleetcode.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 * 描述:
 * @author Jzn
 * @date 2020/7/1
 */
class NoScrollViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    override fun onTouchEvent(arg0: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent?): Boolean {
        return false
    }
}