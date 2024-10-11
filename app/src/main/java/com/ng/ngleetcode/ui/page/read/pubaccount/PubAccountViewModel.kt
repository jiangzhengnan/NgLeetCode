package com.ng.ngleetcode.ui.page.read.pubaccount

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.http.HttpService
import com.ng.ngleetcode.http.PageState
import com.ng.ngleetcode.http.ParentBean
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PubAccountViewModel  (
    private var service: HttpService
) : ViewModel() {
    var viewStates by mutableStateOf(PubAccountViewState())
        private set

    init {
        dispatch(PubAccountViewAction.FetchData)
    }

    fun dispatch(action: PubAccountViewAction) {
        when (action) {
            is PubAccountViewAction.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            flow {
                emit(service.getPublicInformation())
            }.map {
                it.data ?: emptyList()
            }.onStart {
                viewStates = viewStates.copy(pageState = PageState.Loading)
            }.onEach {
                viewStates = viewStates.copy(
                    dataList = it,
                    pageState = PageState.Success(it.isEmpty())
                )
            }.catch {
                viewStates = viewStates.copy(pageState = PageState.Error(it))
            }.collect()
        }
    }
}


class PubAccountViewModelFactory(private val service: HttpService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PubAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PubAccountViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

data class PubAccountViewState(
    val dataList: List<ParentBean> = emptyList(),
    val pageState: PageState = PageState.Loading,
    val listState: LazyListState = LazyListState()
) {
    val size = dataList.size
}

sealed class PubAccountViewAction() {
    object FetchData : PubAccountViewAction()
}