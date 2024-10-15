package com.ng.ngleetcode.ui.page.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.ng.ngleetcode.app.http.ParentBean
import com.ng.ngleetcode.ui.page.code.widgets.CodeView
import com.ng.ngleetcode.ui.page.code.CodePage
import com.ng.ngleetcode.ui.page.profile.ProfilePage
import com.ng.ngleetcode.ui.page.read.ReadPage
import com.ng.ngleetcode.ui.page.read.stucture.list.StructureArticleRefreshableListPage
import com.ng.ngleetcode.ui.page.statistics.StatisticsPage
import com.ng.ngleetcode.ui.page.web.WebData
import com.ng.ngleetcode.ui.page.web.WebViewPage
import com.ng.ngleetcode.ui.widgets.AppSnackBar
import com.ng.ngleetcode.ui.widgets.BottomNavBarView
import com.ng.ngleetcode.utils.fromJson

@Composable
fun MainPage() {
  val navCtrl = rememberNavController()
  val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val scaffoldState = rememberScaffoldState()

  // 使用 remember 来确保 WebView 实例不会在重组时被重新创建
  val webViewState = remember { mutableStateOf<CodeView?>(null) }

  Scaffold(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    bottomBar = {
      when (currentDestination?.route) {
        RouteName.CODE -> BottomNavBarView(navCtrl = navCtrl)
        RouteName.STATISTICS -> BottomNavBarView(navCtrl = navCtrl)
        RouteName.READ -> BottomNavBarView(navCtrl = navCtrl)
        RouteName.PROFILE -> BottomNavBarView(navCtrl = navCtrl)
      }
    },
    content = {
      NavHost(
        modifier = Modifier
          .background(MaterialTheme.colors.background)
          .fillMaxHeight(),
        navController = navCtrl,
        startDestination = RouteName.CODE,
        //去掉渐入渐出动画
        enterTransition = {
          EnterTransition.None
        },
        exitTransition = {
          ExitTransition.None
        },
      ) {
        //题库
        composable(route = RouteName.CODE) {
          CodePage(navCtrl, scaffoldState, webViewState)
        }

        //收藏
        composable(route = RouteName.STATISTICS) {
          StatisticsPage(navCtrl, scaffoldState)
        }

        //阅读
        composable(route = RouteName.READ) {
          ReadPage(navCtrl, scaffoldState)
        }

        //我的
        composable(route = RouteName.PROFILE) {
          ProfilePage(navCtrl, scaffoldState)
        }

        //WebView
        composable(
          route = RouteName.WEB_VIEW + "/{webData}",
          arguments = listOf(navArgument("webData") { type = NavType.StringType })
        ) {
          val args = it.arguments?.getString("webData")?.fromJson<WebData>()
          if (args != null) {
            WebViewPage(webData = args, navCtrl = navCtrl)
          }
        }

        //体系列表
        composable(
          route = RouteName.ARTICLE_LIST + "/{parentData}",
          arguments = listOf(navArgument("parentData") { type = NavType.StringType })
        ) {
          val args = it.arguments?.getString("parentData")?.fromJson<ParentBean>()
          if (args != null) {
            StructureArticleRefreshableListPage(parentData = args,
              navCtrl =
            navCtrl)
          }
        }



      }
    },
    snackbarHost = {
      SnackbarHost(
        hostState = scaffoldState.snackbarHostState
      ) { data ->
        println("show actionLabel = ${data.actionLabel}")
        AppSnackBar(data = data)
      }
    }
  )

}


