package com.ng.base.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/18
 *    @description   :
 */

fun View.showSnackBar(
    text: String, actionText: String? = null, duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackbar.setAction(actionText) {
            block
        }
    }
    snackbar.show()
}

fun View.showSnackBar(
    text: String, actionResId: Int? = null, duration: Int = Snackbar.LENGTH_SHORT,
    block: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionResId != null && block != null) {
        snackbar.setAction(actionResId) {
            block
        }
    }
    snackbar.show()
}