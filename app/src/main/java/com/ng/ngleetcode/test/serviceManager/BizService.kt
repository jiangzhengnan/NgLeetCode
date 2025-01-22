package com.ng.ngleetcode.test.serviceManager

import com.ng.ngleetcode.test.协程启动框架.DagNode

// 服务工厂接口
interface ServiceFactory {
	fun createService(deps: MutableList<BizService>): BizService
}

// 服务销毁器接口
interface ServiceDestroyer {
	fun destroyService(service: BizService)
}

// 服务配置类
class BizService(
	val id: Int,//服务ID，应该是个枚举。
	val factory: ServiceFactory,
	val destroyer: ServiceDestroyer,
	val dependencies: MutableSet<BizService>, // 所依赖的Service
	val isLazy: Boolean //是否懒加载
) : DagNode() {
	override val name: String = id.toString()
	override val depends: MutableSet<String> = dependencies.map { it.name }.toMutableSet()
}


// 业务服务管理器接口
internal interface BizServiceManager {
	fun getService(id: Int): BizService?

	fun initBizServiceManager(configs: Array<BizService>)
}

/**
 * 具体的业务服务管理器实现
 */
object BizServiceManagerImpl : BizServiceManager {
	private val services: MutableList<BizService> = mutableListOf()

	override fun getService(id: Int): BizService? {
		return TODO("Not yet implemented")
	}

	/**
	 * TODO 可以根据Dag节点关系,通过DagUtils做有向图检测。
	 */
	override fun initBizServiceManager(configs: Array<BizService>) {
		TODO("Not yet implemented")
	}
}


