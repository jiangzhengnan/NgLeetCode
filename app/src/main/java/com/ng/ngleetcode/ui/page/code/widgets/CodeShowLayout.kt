package com.ng.ngleetcode.ui.page.code.widgets

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.view.CodeView
import com.ng.ngleetcode.old.model.code.view.Language
import com.ng.ngleetcode.old.model.code.view.Theme

/**
 * 代码展示图
 */
@SuppressLint("ClickableViewAccessibility")
@Composable
fun CodeShowLayout(
  data: CodeNode, onTouchEvent: ((event: MotionEvent) -> Boolean)? = null
) {
  AndroidView(
    modifier = Modifier.fillMaxSize(),
    factory = { context ->
      // Creates view
      CodeView(context).apply {
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
      }
    },
    update = { view ->
      view.code = data.content
      view.apply()
    }
  )
}