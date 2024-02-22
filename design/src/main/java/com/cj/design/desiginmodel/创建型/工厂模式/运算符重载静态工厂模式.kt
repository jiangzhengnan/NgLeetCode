package com.cj.design.desiginmodel.创建型.工厂模式

class 运算符重载静态工厂模式 {

  enum class CarType {
    AUDI, BMW
  }

  interface Car {
    val brand: String

    companion object {
      operator fun invoke(type: CarType): Car {
        return when (type) {
          CarType.AUDI -> Audi()
          CarType.BMW -> BMW()
        }
      }
    }
  }

  class Audi : Car {
    override val brand: String
      get() = "audi"
  }

  class BMW : Car {
    override val brand: String
      get() = "BMW"
  }


}

fun main() {
  println(运算符重载静态工厂模式.Car.invoke(运算符重载静态工厂模式.CarType.AUDI).brand)
}
