package com.ng.ngleetcode.ui.page.read.stucture.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ng.ngleetcode.http.ApiRepo
import com.ng.ngleetcode.http.Article
import com.ng.ngleetcode.http.ListWrapper
import com.ng.ngleetcode.http.PageState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StructureArticleListModel(private val cid: Int) : ViewModel() {

  var viewStates by mutableStateOf(StructureArticleListState())
    private set

  // 当前页码 todo jzn 待做加载更多功能
  var nowPage = 0

  fun dispatch(action: StructureArticleListAction) {
    when (action) {
      is StructureArticleListAction.FetchData -> fetchData()
      is StructureArticleListAction.LoadMoreData -> loadMoreData()
    }
  }

  private fun fetchData() {
    viewModelScope.launch {
      flow {
        emit(ApiRepo.getHttpService().getStructureArticles(nowPage, cid))
      }.map {
        it.data
      }.onStart {
        viewStates = viewStates.copy(pageState = PageState.Loading)
      }.onEach {
        viewStates = viewStates.copy(
          dataList = it,
          pageState = PageState.Success(it?.datas?.isEmpty() == true)
        )
      }.catch {
        viewStates = viewStates.copy(pageState = PageState.Error(it))
      }.collect()
    }
  }

  private fun loadMoreData() {


    nowPage++
    fetchData()
  }

}

data class StructureArticleListState(
  val dataList: ListWrapper<Article>? = null,
  val pageState: PageState = PageState.Loading,
) {
  val size = dataList?.size
}

sealed class StructureArticleListAction() {
  object FetchData : StructureArticleListAction()
  object LoadMoreData : StructureArticleListAction()

}