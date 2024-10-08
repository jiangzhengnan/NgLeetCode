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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.ng.ngleetcode.ui.page.code.CodePage
import com.ng.ngleetcode.ui.page.profile.ProfilePage
import com.ng.ngleetcode.ui.page.read.ReadPage
import com.ng.ngleetcode.ui.widgets.AppSnackBar
import com.ng.ngleetcode.ui.widgets.BottomNavBarView

@Composable
fun MainPage() {
  val navCtrl = rememberNavController()
  val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  val scaffoldState = rememberScaffoldState()
  Scaffold(
    modifier = Modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    bottomBar = {
      when (currentDestination?.route) {
        RouteName.CODE -> BottomNavBarView(navCtrl = navCtrl)
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
          CodePage(navCtrl, scaffoldState)
        }

        //阅读
        composable(route = RouteName.READ) {
          ReadPage(navCtrl, scaffoldState)
        }

        //我的
        composable(route = RouteName.PROFILE) {
          ProfilePage(navCtrl, scaffoldState)
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


