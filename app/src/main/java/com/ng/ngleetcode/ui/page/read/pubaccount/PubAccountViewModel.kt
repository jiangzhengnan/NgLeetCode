package com.ng.ngleetcode.ui.page.read.pubaccount

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.app.http.ApiRepo
import com.ng.ngleetcode.app.http.PageState
import com.ng.ngleetcode.app.http.ParentBean
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PubAccountViewModel  () : ViewModel() {
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
                emit(ApiRepo.getHttpService().getPublicInformation())
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