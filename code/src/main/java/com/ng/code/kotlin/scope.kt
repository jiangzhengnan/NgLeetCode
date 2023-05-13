package com.ng.code.kotlin

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/11
 *    @description   :
 *    https://blog.csdn.net/qq_34691593/article/details/124736775
 */


fun main() {
    val testStr : String? = "my"

    val otherStr = with(testStr) {
        println("this : $this")

        this + "a"
    }
    println("otherStr : $otherStr")



    val testCheck = testStr.let {
        it.isNullOrBlank()
    }

    print(testCheck)

    val empty = "test".let {               // 1
        customPrint(it)                    // 2
        it.isEmpty()                       // 3
    }
    println(" is empty: $empty")


    fun printNonNull(str: String?) {
        println("Printing \"$str\":")

        str?.let {                         // 4
            print("\t")
            customPrint(it)
            println()
        }
    }



    fun printIfBothNonNull(strOne: String?, strTwo: String?) {
        strOne?.let { firstString ->       // 5
            strTwo?.let { secondString ->
                customPrint("$firstString : $secondString")
                println()
            }
        }
    }

    printNonNull(null)
    printNonNull("my string")
    printIfBothNonNull("First","Second")




}

fun customPrint(it: String) {

    println("自定义输出 $it")
}
