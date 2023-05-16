package com.ng.code.kotlin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//sampleStart
// Sequentially executes doWorld followed by "Done"

fun main() {
    runBlocking {
        repeat(50_000) { // 启动大量的协程
            launch {
                delay(5000L)
                print(".")
            }
        }
    }

}


// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}