package com.ng.ngleetcode.ui.page.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ng.ngleetcode.app.http.store.DataStoreUtils
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.ui.page.web.WebData

/**
 * 全局viewMode
 */
class MainViewModel : ViewModel() {

  companion object {
    private const val KEY_THEME = "app_theme"
  }

  // 当前主题（从 DataStore 读取保存的值）
  var theme by mutableStateOf(loadTheme())

  // 右滑进入的web
  var currentWeb: WebData? by mutableStateOf(null)

  private fun loadTheme(): AppTheme.Theme {
    val themeName = DataStoreUtils.readStringData(KEY_THEME, AppTheme.Theme.Light.name)
    return try {
      AppTheme.Theme.valueOf(themeName)
    } catch (e: Exception) {
      AppTheme.Theme.Light
    }
  }

  private fun saveTheme(theme: AppTheme.Theme) {
    DataStoreUtils.saveSyncStringData(KEY_THEME, theme.name)
  }

  fun handIntent(action: MainViewAction) {
    when (action) {
      is MainViewAction.ChangeTheme -> {
        theme = if (theme == AppTheme.Theme.Light) {
          AppTheme.Theme.Dark
        } else {
          AppTheme.Theme.Light
        }
        saveTheme(theme)
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