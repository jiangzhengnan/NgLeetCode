package com.ng.ngleetcode.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.H7


@Composable
fun ArrowRightListItem(
    iconRes: Any,
    title: String,
    msgCount: Int? = null,
    valueText: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                onClick.invoke()
            }
    ) {

        when (iconRes) {
            is Painter -> {
                Icon(
                    painter = iconRes,
                    contentDescription = null,
                    tint = AppTheme.colors.icon,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                        .padding(end = 10.dp)
                )
            }
            is ImageVector -> {
                Icon(
                    imageVector = iconRes,
                    contentDescription = null,
                    tint = AppTheme.colors.icon,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                        .padding(end = 10.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            TextContent(text = title, modifier = Modifier.align(Alignment.CenterVertically))
            if (msgCount != null) {
                Text(
                    text = "（$msgCount）",
                    fontSize = H7,
                    color = AppTheme.colors.error,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        if (valueText.isNotEmpty()) {
            TextContent(
                text = valueText,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Icon(
            Icons.Default.KeyboardArrowRight,
            null,
            tint = AppTheme.colors.textSecondary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
    Divider()
}




