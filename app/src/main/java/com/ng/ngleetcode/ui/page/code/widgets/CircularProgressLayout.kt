package com.ng.ngleetcode.ui.page.code.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ng.ngleetcode.old.model.code.view.CircularProgressView
import com.ng.ngleetcode.ui.page.code.mvi.CodeDrawerViewState

/**
 * 进度圈圈图
 */
@Composable
fun CircularProgressLayout(viewData: CodeDrawerViewState) {
  AndroidView(
    modifier = Modifier.size(90.dp),
    factory = { context ->
      // Creates view
      CircularProgressView(context).apply {
        setValue(viewData)
      }
    },
    update = { view ->
      view.setValue(viewData)
    }
  )
}