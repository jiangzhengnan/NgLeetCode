package com.cj.design.log

// 开关控制
interface SwitchControl {
  fun shouldPrint(message: Any): Boolean
}

// 过滤器
interface FilterStrategy {
  fun filter(message: Any): Boolean
}

// 构造器
interface MessageConstructorStrategy {
  fun constructMessage(message: Any): String
}

// 消息队列
interface MessageQueue {
  fun enqueue(message: Any)
  fun dequeue(batchSize: Int): List<Any>
  fun isOverflowed(): Boolean
}

// 调度器
interface Scheduler {
  fun schedulePrintTask()
}

// 消费者
interface Worker {
  fun print(log: String)
}

class SwitchControlImpl(private val config: String) : SwitchControl {
  // 根据配置判断是否打印
  override fun shouldPrint(message: Any): Boolean {
    return false
  }
}

class FilterStrategyImpl(private val filterMap: String) : FilterStrategy {
  override fun filter(message: Any): Boolean {
    return false
  }
}

class MessageConstructorJsonStrategy(private val config: String) : MessageConstructorStrategy {
  // 根据配置构造JSON
  override fun constructMessage(message: Any): String {
    return ""
  }
}

class ArrayDequeMessageQueue(config: String) : MessageQueue {
  // 实现enqueue, dequeue, isOverflowed方法

  private val queue = ArrayDeque<String>(1)
  override fun enqueue(message: Any) {
  }

  override fun dequeue(batchSize: Int): List<Any> {
    return listOf()
  }

  override fun isOverflowed(): Boolean {
    return false
  }
}

class PrintTaskScheduler(private val messageQueue: MessageQueue, private val worker: Worker) : Scheduler {
  // 实现schedulePrintTask方法
  override fun schedulePrintTask() {
  }
}

class WorkerImpl : Worker {
  override fun print(log: String) {
  }
}

// 工厂类
class ModuleFactory {
  companion object {
    fun createSwitchControl(config: String): SwitchControl = SwitchControlImpl(config)
    fun createFilterStrategy(config: String): FilterStrategy = FilterStrategyImpl(config)
    fun createMessageConstructor(config: String): MessageConstructorStrategy = MessageConstructorJsonStrategy(config)
    fun createMessageQueue(config: String): MessageQueue = ArrayDequeMessageQueue(config)
    fun createScheduler(messageQueue: MessageQueue, worker: Worker): Scheduler = PrintTaskScheduler(messageQueue, worker)
  }
}

// 单例类
object LogFramework {
  private var switchControl: SwitchControl? = null
  private var filterStrategy: FilterStrategy? = null
  private var messageConstructor: MessageConstructorStrategy? = null
  private var messageQueue: MessageQueue? = null
  private var scheduler: Scheduler? = null

  init {
    // 初始化配置并创建模块实例
    val config = loadConfigFromServer() // 开关配置集合
    switchControl = ModuleFactory.createSwitchControl(config["switches"].toString())
    filterStrategy = ModuleFactory.createFilterStrategy(config["filters"].toString())
    messageConstructor = ModuleFactory.createMessageConstructor(config["constructors"].toString())
    messageQueue = ModuleFactory.createMessageQueue(config["queueCapacity"].toString())
    scheduler = ModuleFactory.createScheduler(messageQueue!!, WorkerImpl())
  }

  private fun loadConfigFromServer(): MutableMap<String, String> {
    return mutableMapOf()
  }

  // 框架入口，排障方法
  fun log(message: Any) {
    if (switchControl?.shouldPrint(message) == true && filterStrategy?.filter(message) == true) {
      val logString = messageConstructor?.constructMessage(message) ?: return
      messageQueue?.enqueue(message)
      if (messageQueue?.isOverflowed() == true) {
        // 异常上报逻辑
      }
      scheduler?.schedulePrintTask()
    }
  }

}