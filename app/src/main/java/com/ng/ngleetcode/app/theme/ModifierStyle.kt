package com.ng.ngleetcode.app.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// 绘制小红点
fun Modifier.unread(): Modifier = this.drawWithContent {
  drawContent()
  drawCircle(
    color = Color.Red,
    radius = 5.dp.toPx(), // 半径
    center = Offset(
      size.width - 1.dp.toPx(), 1.dp.toPx() //圆心
    )
  )
}