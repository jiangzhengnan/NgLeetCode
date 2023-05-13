package com.ng.code.kotlin


/**
 * 通过使用 :: 操作符并添加类名来引用构造函数
 */

class TestA {
    var id = 1
    var name = "name"
    var body = "body"
}

fun main() {

    val testA = TestA()
    testA::id.set(1)
    println(testA::name)

    val test = "[a-z]".toRegex()
    println(test.matches("a"))

    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29"))

    val isNumber = numberRegex::matches
    println(isNumber("29"))

    val strings = listOf("abc", "124", "a70")
    println(strings.filter(numberRegex::matches))

    val isNumber2: (CharSequence) -> Boolean = numberRegex::matches

    val matches: (Regex, CharSequence) -> Boolean = Regex::matches

    //属性引用也可以绑定
    val prop = "abc"::length
    println(prop.get())
}