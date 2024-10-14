package com.ng.ngleetcode.ui.page.read.navi

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.http.ApiRepo
import com.ng.ngleetcode.http.NaviWrapper
import com.ng.ngleetcode.http.PageState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NaviViewModel() : ViewModel() {
  var viewStates by mutableStateOf(NaviViewState())
    private set

  init {
    dispatch(NaviViewAction.FetchData)
  }

  fun dispatch(action: NaviViewAction) {
    when (action) {
      is NaviViewAction.FetchData -> fetchData()
    }
  }

  private fun fetchData() {
    viewModelScope.launch {
      flow {
        emit(ApiRepo.getHttpService().getNavigationList())
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

data class NaviViewState(
  val dataList: List<NaviWrapper> = emptyList(),
  val pageState: PageState = PageState.Loading,
  val listState: LazyListState = LazyListState()
) {
  val size = dataList.size
}

sealed class NaviViewAction() {
  object FetchData : NaviViewAction()
}