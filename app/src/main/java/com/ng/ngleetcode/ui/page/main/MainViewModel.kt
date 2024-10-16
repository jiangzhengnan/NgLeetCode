package com.ng.ngleetcode.ui.page.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.ui.page.web.WebData

/**
 * 全局viewMode
 */
class MainViewModel : ViewModel() {

  // 当前主题
  var theme by mutableStateOf(AppTheme.Theme.Light)

  // 右滑进入的web
  var currentWeb: WebData? by mutableStateOf(null)

  fun handIntent(action: MainViewAction) {
    when (action) {
      is MainViewAction.ChangeTheme -> {
        theme = if (theme == AppTheme.Theme.Light) {
          AppTheme.Theme.Dark
        } else {
          AppTheme.Theme.Light
        }
      }
      is MainViewAction.ExitWeb -> {
        currentWeb = null
      }
      is MainViewAction.OpenWeb -> {
        currentWeb = action.webData
      }
    }
  }

}


sealed class MainViewAction {
  object ChangeTheme : MainViewAction() // 清除本地缓存

  data class OpenWeb(val webData: WebData) : MainViewAction() // 打开侧滑web页
  object ExitWeb : MainViewAction() // 关闭侧滑web页


}