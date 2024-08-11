package com.ng.code.util

class KotlinClass {

  // 扩展函数，用于String类型，计算字符串的字节长度
  fun String.byteLength(): Int {
    return this.toByteArray(Charsets.UTF_8).size
  }

  // 使用扩展函数
  val text = "Hello, Kotlin!"
  val length = text.byteLength() // 调用扩展函数

  fun test() {
    val s = "a";
    s.byteLength()

  }

}