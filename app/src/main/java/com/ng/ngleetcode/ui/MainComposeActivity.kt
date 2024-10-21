package com.ng.ngleetcode.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.ui.page.main.MainPage
import com.ng.ngleetcode.ui.page.main.MainViewModel
import com.ng.ngleetcode.ui.page.web.RightWebViewPage

/**
 * 主页Activity
 */
class MainComposeActivity : ComponentActivity() {

  private val mainViewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme(mainViewModel.theme) {

        Box {
          HomeEntry()
          RightWebViewPage(mainViewModel)
        }
      }
    }
  }

  @Composable
  fun HomeEntry() {
    //是否闪屏页
    var isSplash by remember { mutableStateOf(true) }
      if (isSplash) {
        SplashPage(this) { isSplash = false }
      } else {
        MainPage(mainViewModel)
      }
  }

}
