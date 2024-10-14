package com.ng.ngleetcode.ui.page.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.BottomNavBarHeight
import com.ng.ngleetcode.theme.black
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
    AppToolsBar(title = "总体情况", backgroundColor = AppTheme.colors.mainColor, textColor = black)
    //  进度圆圈
    AndroidView(
      modifier = Modifier
        .width(250.dp)
        .height(250.dp)
        .align(Alignment.CenterHorizontally), // Occupy the max size in the Compose UI
      // tree
      factory = { context ->
        // Creates view
        CircularAnimProgressView(context).apply {
          setData("本轮学习(自24年7月12日起)", "完成：50题", "总计：150题")
        }
      },
      update = { view ->
        // 视图已膨胀或此块中读取的状态已更新
        // 如有必要，在此处添加逻辑
        // 由于selectedItem在此处阅读，AndroidView将重新组合
        // 每当状态发生变化时
        // 撰写示例->查看通信
      }
    )

    Text(
      text = "收藏列表", fontSize = 18.sp, color = black, fontWeight = FontWeight.Bold, modifier =
      Modifier.padding(
        top = 8.dp,
        start = 8.dp
      )
    )

    //todo jzn 实现收藏列表

  }
}

