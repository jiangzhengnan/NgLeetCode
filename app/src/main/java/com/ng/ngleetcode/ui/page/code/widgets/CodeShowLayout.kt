package com.ng.ngleetcode.ui.page.code.widgets

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.view.CodeView
import com.ng.ngleetcode.old.model.code.view.Language
import com.ng.ngleetcode.old.model.code.view.Theme

/**
 * 代码展示组件
 */
@SuppressLint("ClickableViewAccessibility")
@Composable
fun CodeShowLayout(
  webViewState: MutableState<CodeView?>,
  data: CodeNode, onTouchEvent: ((event: MotionEvent) -> Boolean)? = null
) {

  AndroidView(
    modifier = Modifier.fillMaxSize(),
    factory = { context ->
      webViewState.value ?: CodeView(context).apply {
        webViewState.value = this
        theme = Theme.ANDROIDSTUDIO
        language = Language.JAVA
        isWrapLine = false
        fontSize = 8F
        isZoomEnabled = true
        isShowLineNumber = false
        startLineNumber = 0
        code = data.content
        if (onTouchEvent != null) {
          setOnTouchListener { _, event ->
            onTouchEvent(event)
          }
        }
        apply()
        // 赋值
        webViewState.value = this@apply
      }
    },
    update = { view ->
      // 更新 WebView 的内容
      if (view.code != data.content) {
        view.code = data.content
        view.apply()
      }
    }
  )

//  // 11
//  AndroidView(
//    modifier = Modifier.fillMaxSize(),
//    factory = { context ->
//      // Creates view
//      CodeView(context).apply {
//        theme = Theme.ANDROIDSTUDIO
//        language = Language.JAVA
//        isWrapLine = false
//        fontSize = 8F
//        isZoomEnabled = true
//        isShowLineNumber = false
//        startLineNumber = 0
//
//        code = data.content
//
//        if (onTouchEvent != null) {
//          setOnTouchListener { _, event ->
//            onTouchEvent(event)
//          }
//        }
//        apply()
//      }
//    },
//    update = { view ->
//      if (view.code != data.content) {
//        view.code = data.content
//        view.apply()
//      }
//    }
//  )
}