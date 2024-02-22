package com.cj.design.desiginmodel.结构型.装饰器模式

class 委托实现装饰器模式 {

  class ListDecorator<T>(val innerSet: List<T> = listOf()) : List<T> by innerSet {
    override fun contains(element: T): Boolean {
      print("Do other thing")
      return innerSet.contains(element)
    }
  }


}

fun main() {
  val contains = 委托实现装饰器模式.ListDecorator(listOf("ss")).contains("s")

}