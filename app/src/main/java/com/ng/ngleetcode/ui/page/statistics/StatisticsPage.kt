package com.ng.ngleetcode.ui.page.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.BottomNavBarHeight
import com.ng.ngleetcode.ui.widgets.AppToolsBar

@Composable
fun StatisticsPage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
) {

  Column(
    modifier = Modifier
      .padding(bottom = BottomNavBarHeight)
      .fillMaxSize()
      .background(color = AppTheme.colors.mainColor)
  ) {
    AppToolsBar(title = "统计")

  }
}