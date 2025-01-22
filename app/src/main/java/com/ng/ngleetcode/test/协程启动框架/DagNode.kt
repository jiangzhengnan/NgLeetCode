package com.ng.ngleetcode.test.协程启动框架

class MMKvStore : DagNode() {
	override val name: String = "MMKvStore"
	override val depends: MutableSet<String> = mutableSetOf()
}

class AiSummarizeService : DagNode() {
	override val name: String = "AiSummarizeService"
	override val depends: MutableSet<String> = mutableSetOf("MMKvStore")
}

class AppInitializer : DagNode() {
	override val name: String = "AppInitializer"
	override val depends: MutableSet<String> = mutableSetOf("MMKvStore")
}

class PlayerService : DagNode() {
	override val name: String = "PlayerService"
	override val depends: MutableSet<String> = mutableSetOf("RecordService")
}

class RecordService : DagNode() {
	override val name: String = "RecordService"
	override val depends: MutableSet<String> = mutableSetOf("PlayerService")
}

fun main() {
	// 正常情况
	val node1 = MMKvStore()
	val node2 = AiSummarizeService()
	val node3 = AppInitializer()
	val nodeList = mutableListOf(node1, node2, node3)
	DagUtils.checkDag(nodeList)
	println("DAG generated successfully without circular dependency")
	DagUtils.printDAG(node1)

	// 循环依赖情况
	val nodeA = PlayerService()
	val nodeB = RecordService()
	val nodeListWithCycle = mutableListOf(nodeA, nodeB)
	try {
		DagUtils.checkDag(nodeListWithCycle)
	} catch (e: IllegalArgumentException) {
		println("Caught circular dependency: ${e.message}")
	}
}