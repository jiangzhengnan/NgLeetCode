package com.ng.ngleetcode.ui.page.code.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ng.ngleetcode.R
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewAction
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModel

@Composable
fun FloatingActionMenu(
  modifier: Modifier,
  expanded: Boolean,
  onExpandToggle: () -> Unit,
  codeViewModel: CodeViewModel
) {


  Column(modifier = modifier) {
    // 子按钮，使用LazyColumn实现展开和折叠效果
    if (expanded) {
      LazyColumn(
        modifier = Modifier.align(Alignment.CenterHorizontally)
      ) {
        item {
          FloatingActionItem(clickEvent = {
            codeViewModel.handIntent(CodeViewAction.ShowLeftCode)

          }, id = R.drawable.ic_baseline_keyboard_arrow_left_24)
        }

        item {
          FloatingActionItem(clickEvent = {
            codeViewModel.handIntent(CodeViewAction.ShowRightCode)
          }, id = R.drawable.ic_baseline_keyboard_arrow_right_24)
        }

        item {
          FloatingActionItem(clickEvent = {
            codeViewModel.handIntent(CodeViewAction.GetRandomCode)

          }, id = R.drawable.ic_baseline_refresh_24)
        }

        item {
          FloatingActionItem(clickEvent = {
            codeViewModel.handIntent(CodeViewAction.ToggleCodeState)
          }, id = R.drawable.ic_baseline_check_24)
        }
      }
    }

    // 主按钮
    FloatingActionButton(
      modifier = Modifier
        .size(40.dp)
        .align(Alignment.CenterHorizontally),
      onClick = onExpandToggle
    ) {
      Icon(
        imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
        contentDescription = "菜单"
      )
    }
  }


}

@Composable
fun FloatingActionItem(clickEvent: () -> Unit, @DrawableRes id: Int) {
  FloatingActionButton(
    onClick = clickEvent,
    modifier = Modifier
      .size(30.dp)
      .background(Color.Transparent)
  ) {
    Icon(
      painter = painterResource(id = id),
      contentDescription = null
    )
  }
  Spacer(modifier = Modifier.height(8.dp))
}