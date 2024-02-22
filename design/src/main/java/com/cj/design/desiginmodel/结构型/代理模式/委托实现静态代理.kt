package com.cj.design.desiginmodel.结构型.代理模式

class 委托实现静态代理 {

  interface Animal {
    fun roar()
  }

  class Lion : Animal {

    override fun roar() {
      println("Roar! I am a lion!")
    }

  }

  class AnimalProxy(val animal: Animal) : Animal by animal {


    override fun roar() {
      println("Before the roar...")
      animal.roar()

      println("After the roar...")
    }
  }


}

/**
 * 在这个示例中，我们定义了一个Animal接口和一个具体的实现类Lion。我们想要在不改变Lion类的情况下，在其roar()方法前后添加一些自定义逻辑。
 * 我们定义了一个名为AnimalProxy的类，它实现了Animal接口并通过类委托将所有的Animal接口方法委托给一个传入的Animal对象。
 * 我们还在AnimalProxy类中重写了roar()方法，并在代理调用原始对象的roar()方法前后添加了自定义逻辑。
 * 在main()函数中，我们创建了一个Lion实例，并将其传递给我们创建的AnimalProxy实例。然后我们调用Animal接口的roar()方法，
 * 事实上是调用了AnimalProxy对象的roar()方法，从而激活了前面和后面添加的自定义逻辑。
 */
fun main() {
  // 使用代理对象代理Lion
  val lion = 委托实现静态代理.Lion()
  val animalProxy = 委托实现静态代理.AnimalProxy(lion)
  animalProxy.roar()
}