package com.ng.ngleetcode.ui.page.read.navi

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.http.HttpService
import com.ng.ngleetcode.http.NaviWrapper
import com.ng.ngleetcode.http.PageState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NaviViewModel(
  private var service: HttpService
) : ViewModel() {
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
        emit(service.getNavigationList())
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

class NaviViewModelFactory(private val service: HttpService) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(NaviViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return NaviViewModel(service) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
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