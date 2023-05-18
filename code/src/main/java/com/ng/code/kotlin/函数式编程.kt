package com.ng.code.kotlin

import android.content.Context
import android.content.SharedPreferences

/*

定义一个函数类型
(String,Int) -> Unit  括号内两个是参数， 右边是返回值


应该尽量使用内联函数

 */
fun example(a: String, b: Int, methodOut: (String, Int) -> Unit) {
    methodOut(a, b)
}

fun myMehotd(a: String, b: Int) {
    println("$a $b")
}

fun main() {
    //1.基础调用方式
    example("hello", 123, ::myMehotd)

    //2.Lambda调用方式，缺点，创建匿名实例的开销
    example("hello", 123) { a, b ->
        println("$a $b")
    }

    //3.内联函数，无开销 (Kotlin编译器会将内联函数中的代码在编译的时候自动替换到调用的地方)
    inlineExample("hello", 123) { a, b ->
        println("$a $b")
    }

    val context: Context? = null
    context?.getSharedPreferences("key", Context.MODE_PRIVATE)?.open {
        putString("key1", "value1");
    }
}

//4.优化sp
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

inline fun inlineExample(a: String, b: Int, methodOut: (String, Int) -> Unit) {
    methodOut(a, b)
}

//noinline 不内联
inline fun inlineExample2(block1: () -> Unit, noinline block2: () -> Unit) {
}

/*
crossinline 关键字
 */

//会报错，因为内联函数的lambda表达式中允许使用return关键字，和高阶函数的匿名类视线中不允许使用return
// 关键字之间造成了冲突
//inline fun runRunnable(block1: () -> Unit) {
//    val runnable = Runnable{
//        block1()
//    }
//    runnable.run()
//}

//加上crossinline就不会报错了，因为crossinline是一个契约，保证在内联函数的lambda表达式中一定不会使用return关键字
//使用crossinline之后，就不能使用return整体返回了，但是还是可以使用return@runRunnable局部返回
inline fun runRunnable(crossinline block1: () -> Unit) {
    val runnable = Runnable {
        block1()
    }
    runnable.run()
}

fun test() {
    runRunnable {
        println("start")
        //return 酱紫不行
        return@runRunnable //这样是可以的～！
    }
}




