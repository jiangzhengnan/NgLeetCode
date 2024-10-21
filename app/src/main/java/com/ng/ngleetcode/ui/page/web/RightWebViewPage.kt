package com.ng.ngleetcode.ui.page.web

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ng.base.utils.MLog
import com.ng.ngleetcode.R
import com.ng.ngleetcode.app.extension.offsetPercent
import com.ng.ngleetcode.app.theme.ToolBarHeight
import com.ng.ngleetcode.ui.page.main.MainViewAction
import com.ng.ngleetcode.ui.page.main.MainViewModel
import com.ng.ngleetcode.ui.widgets.AppToolsBar
import com.ng.ngleetcode.app.utils.SizeUtils

/**
 * 侧滑进入的webView
 */
@SuppressLint("UseCompatLoadingForDrawables")
@Composable
fun RightWebViewPage(
  viewModel: MainViewModel
) {
  val offsetPercentX by animateFloatAsState(if (viewModel.currentWeb != null) 0f else 1f)
  val webData = viewModel.currentWeb
  var ctrl: WebViewCtrl? by remember { mutableStateOf(null) }

  val targetUrl = webData?.url.toString()
  var webView: WebView? = null
  Box(modifier = Modifier.offsetPercent(offsetPercentX)) {
    var isRefreshing: Boolean by remember { mutableStateOf(false) }
    val refreshState = rememberSwipeRefreshState(isRefreshing)
    AndroidView(
      modifier = Modifier
        .padding(top = ToolBarHeight)
        .fillMaxSize(),
      factory = { context ->
        FrameLayout(context).apply {
          layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
          )
          val progressView = ProgressBar(context).apply {
            layoutParams = ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              SizeUtils.dp2px(2f)
            )
            progressDrawable =
              context.resources.getDrawable(R.drawable.horizontal_progressbar)
            indeterminateTintList =
              ColorStateList.valueOf(context.resources.getColor(R.color.teal_200))
          }
          webView = WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
          }
          addView(webView)
          addView(progressView)
          ctrl = WebViewCtrl(this, targetUrl, onWebCall = { isFinish ->
            isRefreshing = !isFinish
          })
          ctrl?.initSettings()
          MLog.d("webView 组合：" + targetUrl)
        }
      },
      update = {
        MLog.d("webView 刷新：" + targetUrl)
        ctrl?.refresh(targetUrl)
      }
    )
    AppToolsBar(title = webData?.title ?: "标题", onBack = {
//      ctrl?.onDestroy() 这里如果销毁了再进就得重建
//      ctrl = null
      viewModel.handIntent(MainViewAction.ExitWeb)
    })
  }

}
