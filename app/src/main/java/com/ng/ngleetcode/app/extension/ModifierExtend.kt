package com.ng.ngleetcode.app.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
  drawContent()
  if (show) {
    drawCircle(color, 5.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx()))
  }
}


fun Modifier.offsetPercent(offsetPercentX: Float = 0f, offsetPercentY: Float = 0f) =
  this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
      val offsetX = (offsetPercentX * placeable.width).roundToInt()
      val offsetY = (offsetPercentY * placeable.height).roundToInt()
      placeable.placeRelative(offsetX, offsetY)
    }
  }