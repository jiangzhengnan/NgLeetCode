package com.ng.ngleetcode.ui.page.read.stucture.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ng.base.utils.MLog
import com.ng.ngleetcode.app.http.Article
import com.ng.ngleetcode.app.http.ParentBean
import com.ng.ngleetcode.app.http.WebData
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.app.theme.black
import com.ng.ngleetcode.app.theme.hintText
import com.ng.ngleetcode.ui.page.main.RouteName
import com.ng.ngleetcode.ui.widgets.AppToolsBar
import com.ng.ngleetcode.utils.RouteUtils
import com.ng.ngleetcode.utils.RouteUtils.back

/**
 * 某个体系下对应的文章列表
 */
@Composable
fun StructureArticleRefreshableListPage(
  parentData: ParentBean,
  navCtrl: NavHostController,
  viewModel: StructureArticleListModel = viewModel(
    factory = StructureArticleListViewModelFactory(
      parentData.id
    )
  )
) {
  // 记录列表状态
  val lazyListState = rememberLazyListState()
  Column(modifier = Modifier.fillMaxSize()) {
    AppToolsBar(title = parentData.name.toString(), onBack = {
      navCtrl.back()
    })
    SwipeRefresh(state = rememberSwipeRefreshState(viewModel.viewStates.isRefresh), onRefresh = {
      viewModel.dispatch(StructureArticleListAction.RefreshData)
    }) {
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(vertical = 8.dp)
      ) {
        viewModel.viewStates.dataList?.datas?.forEachIndexed { position, chapter1 ->
          item {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                  val lastItemIndex = lazyListState.layoutInfo.visibleItemsInfo.last().index
                  if (viewModel.viewStates.hasMoreData &&
                    !viewModel.viewStates.isRefresh &&
                    lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                    lastItemIndex == (viewModel.viewStates.size - 1)
                  ) {
                    MLog.d(
                      "onGloballyPositioned\n" +
                          "当前size：" + viewModel.viewStates.size +
                          "总数：" + viewModel.viewStates.totalSize +
                          "是否正在刷新：" + viewModel.viewStates.isRefresh +
                          "最后一个可见项:" + lastItemIndex
                    )
                    MLog.d("需要加载更多了")
                    viewModel.dispatch(StructureArticleListAction.LoadMoreData)
                  }

                },
              contentAlignment = Alignment.Center
            ) {

              StructureArticleItem(modifier = Modifier, chapter1, onSelect = { bean ->
                RouteUtils.navTo(
                  navCtrl = navCtrl,
                  destinationName = RouteName.WEB_VIEW,
                  args = WebData(title = bean.title, url = bean.link.toString())
                )
              })
            }
            if (position <= viewModel.viewStates.size - 1) {
              Divider(
                startIndent = 10.dp,
                color = AppTheme.colors.divider,
                thickness = 0.8f.dp
              )
            }
          }
        }

      }
    }
  }

  // 初始化加载数据
  var isInitialized by rememberSaveable { mutableStateOf(false) }
  DisposableEffect(Unit) {
    MLog.d("StructurePage - onStart")
    if (!isInitialized) {
      viewModel.dispatch(StructureArticleListAction.FetchData)
      isInitialized = true
      MLog.d("StructurePage - fetchData")
    }
    onDispose {
      MLog.d("StructurePage - onDispose")
    }
  }
}

@Composable
fun StructureArticleItem(
  modifier: Modifier,
  article: Article,
  onSelect: (parent: Article) -> Unit = {},
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(all = 10.dp)
      .clickable {
        onSelect.invoke(article)
      },
  ) {

    Box(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = article.author.toString(), fontSize = 11.sp, color = hintText,
        modifier = Modifier.align(Alignment.CenterStart)
      )
      Text(
        text = article.niceDate.toString(), fontSize = 11.sp, color = hintText,
        modifier = Modifier.align(Alignment.CenterEnd)
      )
    }
    Text(
      text = article.title.toString(), fontSize = 15.sp, color = black,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 6.dp, bottom = 6.dp),
      fontWeight = FontWeight.Bold
    )

    Box(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = article.superChapterName.toString() + "/" + article.chapterName.toString(),
        fontSize = 11.sp,
        color = hintText,
        modifier = Modifier.align(Alignment.CenterEnd)
      )
    }

  }
}
