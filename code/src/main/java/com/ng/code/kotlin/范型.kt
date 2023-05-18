package com.ng.code.kotlin

import android.content.ContentResolver
import android.net.Uri

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/16
 *    @description   :
 */

class MyClass {
    fun <T> method(params: T): T {
        return params
    }
}

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}

fun test(block1: (String, Int) -> Unit) {
    block1("haha", 1)

}

fun main() {
    val mClass = MyClass()
    val result = mClass.method<Int>(123)

    var resolver: ContentResolver? = null
    resolver?.query(Uri.parse(""), null, null, null, null)?.build {
        while (moveToNext()) {

        }
        close()
    }

}
