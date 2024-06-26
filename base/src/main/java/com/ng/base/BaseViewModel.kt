package com.ng.base

import android.app.Application
import androidx.lifecycle.*
import com.ng.base.event.Message
import com.ng.base.event.SingleLiveEvent
import com.ng.base.exception.ExceptionHandle
import com.ng.base.exception.IBaseResponse
import com.ng.base.exception.ResponseThrowable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {
    var errorResult = MutableLiveData<String>()

    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        block()
    }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }.flowOn(Dispatchers.IO)
    }

    fun <T> launchFlow(
        block: suspend () -> T,
        success: (T) -> Unit,
        errorE: suspend (cause: Throwable) -> Unit = {
            defUI.toastEvent.postValue("${it.message}")
            //MLog.d("http_launchGo :${it.code}:${it.errMsg}")
            it.printStackTrace()
        }, isShowDialog: Boolean = true
    ) {
        launchUI {
            flow {
                emit(block())
            }.flowOn(Dispatchers.IO).onStart {
                //显示加载框
                if (isShowDialog) defUI.showDialog.call()
            }.onCompletion {
                if (isShowDialog) defUI.dismissDialog.call()
            }.catch {
                defUI.dismissDialog.call()
                errorE(it)
            }.collectLatest {
                defUI.dismissDialog.call()
                success(it)
            }
        }
    }

    /**
     *  不过滤请求结果
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
            //MLog.d("http_launchGo :${it.code}:${it.errMsg}")
            it.printStackTrace()
        },
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                withContext(Dispatchers.IO) { block },
                { error(it) },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T> launchOnlyResult(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: (T?) -> Unit,
        error: (ResponseThrowable) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
        },
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                { withContext(Dispatchers.IO) { block() } },
                { res ->
                    executeResponse(res) { success(it) }
                },
                {
                    error(it)
                },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T> launchOnlyResultForBody(
        block: suspend CoroutineScope.() -> T,
        success: (T) -> Unit,
        error: (ResponseThrowable) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
        },
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleExceptionForResponseBody<T>(
                { withContext(Dispatchers.IO) { block() } },
                { res ->
                    success(res)
                },
                {
                    error(it)
                },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
        response: IBaseResponse<T>,
        success: suspend CoroutineScope.(T?) -> Unit
    ) {
        coroutineScope {
            if (response.suc()) success(response.data())
            else throw ResponseThrowable(response.code(), response.msg().toString())
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleException(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: suspend CoroutineScope.(IBaseResponse<T>) -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleExceptionForResponseBody(
        block: suspend CoroutineScope.() -> T,
        success: suspend CoroutineScope.(T) -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }


    /**
     * 异常统一处理
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }

    /**
     * 无更多数据
     */
    val footLiveDate = MutableLiveData<Any>()

    /**
     * 无数据
     */
    val emptyLiveDate = MutableLiveData<Any>()

    /**
     * 处理列表是否有更多数据
     */
    protected fun<T> handleList(listLiveData: LiveData<MutableList<T>>, pageSize:Int = 20){
        val listSize = listLiveData.value?.size?:0
        if (listSize % pageSize != 0){
            footLiveDate.value = 1
        }
    }


    /**
     * UI事件
     */
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val showError by lazy { SingleLiveEvent<String>() }
        val showEmpty by lazy { SingleLiveEvent<String>() }

        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val msgEvent by lazy { SingleLiveEvent<Message>() }


    }
}