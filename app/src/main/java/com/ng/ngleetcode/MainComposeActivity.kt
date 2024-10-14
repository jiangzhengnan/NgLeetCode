package com.ng.ngleetcode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.ui.SplashPage
import com.ng.ngleetcode.ui.page.main.MainPage

/**
 * 主页Activity
 */
class MainComposeActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HomeEntry()
    }
  }

  @Composable
  fun HomeEntry() {
    //是否闪屏页
    var isSplash by remember { mutableStateOf(true) }
    AppTheme {
      if (isSplash) {
        SplashPage (this){ isSplash = false }
      } else {
        MainPage()
      }
    }
  }


}
