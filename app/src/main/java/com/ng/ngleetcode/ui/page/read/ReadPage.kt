package com.ng.ngleetcode.ui.page.read

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.app.theme.BottomNavBarHeight
import com.ng.ngleetcode.ui.page.read.navi.NaviPage
import com.ng.ngleetcode.ui.page.read.pubaccount.PublicAccountPage
import com.ng.ngleetcode.ui.page.read.stucture.StructurePage
import com.ng.ngleetcode.ui.widgets.TextTabBar
import kotlinx.coroutines.launch

/**
 * 知识体系部分
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ReadPage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
  categoryIndex: Int = 0,
  viewModel: ReadViewModel = viewModel()
) {

  val titles = viewModel.titles
  Box(modifier = Modifier.padding(bottom = BottomNavBarHeight)) {
    Column {
      val pagerState = rememberPagerState(
        initialPage = categoryIndex,
      )
      val scopeState = rememberCoroutineScope()

      Row {
        TextTabBar(
          index = pagerState.currentPage,
          tabTexts = titles,
          modifier = Modifier.weight(1f),
          contentAlign = Alignment.CenterStart,
          onTabSelected = { index ->
            scopeState.launch {
              pagerState.scrollToPage(index)
            }
          }
        )
      }

      HorizontalPager(
        count = titles.size,
        state = pagerState,
        modifier = Modifier.background(AppTheme.colors.background)
      ) { page ->
        when (page) {
          0 -> StructurePage(navCtrl)
          1 -> NaviPage(navCtrl)
          2 -> PublicAccountPage(navCtrl)
        }
      }
    }
  }


}