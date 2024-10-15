package com.ng.ngleetcode.app.http

import kotlinx.coroutines.*

/**
 * des 基础数据层
 * @date 2020/5/18
 * 
 *
 * @param coroutineScope 注入viewModel的coroutineScope用于协程管理
 * @param errorLiveData 业务出错或者爆发异常，由errorLiveData通知视图层去处理
 */
open class BaseRepository {

    /**
     * 在协程作用域中切换至IO线程
     */
    protected suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }

}