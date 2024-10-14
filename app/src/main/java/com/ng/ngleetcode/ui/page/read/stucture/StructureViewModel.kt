package com.ng.ngleetcode.ui.page.read.stucture

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.http.*
import com.ng.ngleetcode.old.model.tree.http.http.TreeRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StructureViewModel(
  private var service: HttpService
) : ViewModel() {
  var viewStates by mutableStateOf(StructureViewState())
    private set

  private val repo by lazy { TreeRepo() }

//  init {
//    dispatch(StructureViewAction.FetchData)
//  }

  fun dispatch(action: StructureViewAction) {
    when (action) {
      is StructureViewAction.FetchData -> fetchData()
    }
  }

  private fun fetchData() {
    viewModelScope.launch {
      flow {
        emit(ApiRepo.getHttpService().getStructureList())
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

class StructureViewModelFactory(private val service: HttpService) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(StructureViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return StructureViewModel(service) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}

data class StructureViewState(
  val dataList: List<ParentBean> = emptyList(),
  val pageState: PageState = PageState.Loading,
  val listState: LazyListState = LazyListState()
) {
  val size = dataList.size
}

sealed class StructureViewAction() {
  object FetchData : StructureViewAction()
}