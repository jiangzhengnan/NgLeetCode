package com.ng.ngleetcode.ui.page.code.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.ngleetcode.R
import com.ng.ngleetcode.old.model.code.bean.CodeDirNode
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewAction
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ExpandableGroup(group: CodeDirNode, index: Int) {
  val toggleExpanded: () -> Unit = { group.expandedState.value = !group.expandedState.value }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(35.dp)
      .clickable { toggleExpanded() },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = group.title,
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp),
      fontSize = 15.sp
    )
    IconButton(onClick = toggleExpanded) {
      Icon(
        painter = if (group.expandedState.value)
          painterResource(id = R.drawable.ic_iv_down) else
          painterResource(id = R.drawable.ic_iv_right),
        contentDescription = "Expand or collapse group",
        modifier = Modifier.size(15.dp)
      )
    }
  }
}

@Composable
fun ExpandableItems(
  items: List<BaseNode>,
  expanded: Boolean,
  codeViewModel: CodeViewModel,
  scope: CoroutineScope,
  drawerState: DrawerState,
) {
  if (expanded) {
    Column(modifier = Modifier.background(color = AppTheme.colors.mainColor)) {
      items.forEach { item ->
        val codeNode: CodeNode = item as CodeNode
        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .padding(start = 16.dp)
            .background(color = AppTheme.colors.card)
        )
        Text(
          text = codeNode.title,
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 6.dp, bottom = 6.dp)
            .clickable {
              val showCodeAction = CodeViewAction.ShowCode(codeNode)
              codeViewModel.handIntent(showCodeAction)
              scope.launch { drawerState.close() }
            },
          fontSize = 10.sp,
        )
      }
    }
  }
}

@Composable
fun CodeDrawerGroupListView(
  groups: List<CodeDirNode?>?,
  codeViewModel: CodeViewModel,
  scope: CoroutineScope,
  drawerState: DrawerState,
) {
  if (groups.isNullOrEmpty()) {
    return
  }
  LazyColumn {
    items(
      items = groups,
      key = { group -> group?.title.toString() }
    ) { group ->
      if (group == null) {
        return@items
      }
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(2.dp)
          .background(color = AppTheme.colors.mainColor)
      )
      ExpandableGroup(group = group, index = 0)
      ExpandableItems(
        items = group.childNode, expanded = group.expandedState.value,
        codeViewModel, scope, drawerState
      )
    }
  }
}