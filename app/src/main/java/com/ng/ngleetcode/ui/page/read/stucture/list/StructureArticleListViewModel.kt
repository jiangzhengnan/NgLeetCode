package com.ng.ngleetcode.ui.page.read.stucture.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.app.http.ApiRepo
import com.ng.ngleetcode.app.http.Article
import com.ng.ngleetcode.app.http.ListWrapper
import com.ng.ngleetcode.app.http.PageState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StructureArticleListModel(private val cid: Int) : ViewModel() {

  var viewStates by mutableStateOf(StructureArticleListState())
    private set

  // 当前页码
  var nowPage = 0

  fun dispatch(action: StructureArticleListAction) {
    when (action) {
      is StructureArticleListAction.FetchData -> fetchData(false)
      is StructureArticleListAction.LoadMoreData -> {
        nowPage++
        fetchData(true)
      }
      is StructureArticleListAction.RefreshData -> {
        nowPage = 0
        fetchData(false)
      }
    }
  }

  private fun fetchData(isLoadMore: Boolean) {
    val originDataList: ListWrapper<Article>? = viewStates.dataList?.copy()
    // 先修改状态
    viewStates = viewStates.copy(pageState = PageState.Loading)
    viewModelScope.launch {
      flow {
        emit(ApiRepo.getHttpService().getStructureArticles(nowPage, cid))
      }.map {
        it.data
      }.onStart {
        //viewStates = viewStates.copy(pageState = PageState.Loading)
      }.onEach {
        if (isLoadMore) {
          if (it?.datas?.isNotEmpty() == true && originDataList?.datas?.isNotEmpty() == true) {
//            it.datas.addAll(originDataList.datas)
            originDataList.datas.addAll(it.datas)
            it.datas = originDataList.datas
          }
        }
        viewStates = viewStates.copy(
          dataList = it,
          pageState = PageState.Success(it?.datas?.isEmpty() == true)
        )
      }.catch {
        viewStates = viewStates.copy(pageState = PageState.Error(it))
      }.collect()
    }
  }

}


class StructureArticleListViewModelFactory(private val cid: Int) : ViewModelProvider
.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(StructureArticleListModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return StructureArticleListModel(cid) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}


data class StructureArticleListState(
  val dataList: ListWrapper<Article>? = null,
  val pageState: PageState = PageState.Loading,
) {
  val size = dataList?.datas?.size ?: 0
  val isRefresh = (pageState == PageState.Loading)
  val totalSize = dataList?.total ?: 0
  val hasMoreData = size < totalSize
}

sealed class StructureArticleListAction() {
  object RefreshData : StructureArticleListAction()
  object FetchData : StructureArticleListAction()
  object LoadMoreData : StructureArticleListAction()

}