package com.ng.code.kotlin

import java.io.File


fun main() {

    val files = File("test").listFiles()

    // 为空则输出null
    println(files?.size)

    // 为空则输出empty
    println(files?.size ?: "empty")

    // 为空则执行block
    val filesSize = files?.size ?: run {
        println("is empty")
    }

    println(filesSize)

}