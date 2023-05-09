package com.ng.base.utils

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/09
 *    @description   :
 */
object BindingUtil {

    /**
     * 得到 Binding 的实例
     *
     * @param viewBindingClass Class
     * @param layoutInflater LayoutInflater
     * @param <Binding> Binding
     * @return Binding
    </Binding> */
    fun <Binding> createViewBinding(
        viewBindingClass: Class<out ViewBinding>?,
        layoutInflater: LayoutInflater
    ): Binding? {
        try {
            val method: Method? = viewBindingClass?.getMethod("inflate", LayoutInflater::class.java)
            val viewBinding = method?.invoke(
                viewBindingClass,
                layoutInflater
            ) as ViewBinding
            return viewBinding as Binding
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}