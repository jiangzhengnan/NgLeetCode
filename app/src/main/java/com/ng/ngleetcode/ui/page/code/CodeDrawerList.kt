package com.ng.ngleetcode.ui.page.code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.green1
import com.ng.ngleetcode.theme.red
import com.ng.ngleetcode.theme.warn
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewAction
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModel
import com.ng.ngleetcode.ui.page.code.widgets.CircularProgressLayout
import com.ng.ngleetcode.ui.page.code.widgets.CodeDrawerGroupListView
import com.ng.ngleetcode.ui.page.code.widgets.Group
import kotlinx.coroutines.CoroutineScope

/**
 * 侧边栏题库列表
 */
@Composable
fun CodeDrawerList(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
  scope: CoroutineScope,
  drawerState: DrawerState,
  codeViewModel: CodeViewModel
) {

  val viewStates = remember { codeViewModel.codeDrawerListState }

  Column(
    Modifier
      .fillMaxSize()
      .background(AppTheme.colors.mainColor)
  ) {
    // head布局
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)
        .padding(10.dp)
    ) {
      CircularProgressLayout(viewStates.value)

      Column(modifier = Modifier.fillMaxHeight()) {
        Text(
          text = "简单: " + viewStates.value.easyRead + " / " + viewStates.value.easyCount,
          color = green1,
          modifier = Modifier
            .weight(1f)
        )
        Text(
          text = "中等: " + viewStates.value.midRead + " / " + viewStates.value.midCount,
          color = warn,
          modifier = Modifier.weight(1f)
        )
        Text(
          text = "困难: " + viewStates.value.hardRead + " / " + viewStates.value.hardCount,
          color = red,
          modifier = Modifier.weight(1f)
        )
      }
    }
    // 列表
    TestList()
  }

  /*
    val items =
    listOf(
      Icons.Default.AccountCircle,
    )
  val selectedItem = remember { mutableStateOf(items[0]) }
  Column(Modifier.verticalScroll(rememberScrollState())) {
    Spacer(Modifier.height(12.dp))
    items.forEach { item ->
      NavigationDrawerItem(
        icon = { Icon(item, contentDescription = null) },
        label = {
          Text(
            text = "size:" + codeViewModel.codeDrawerListState.value
              .codeListData?.size
          )
        },
        selected = item == selectedItem.value,
        onClick = {
          scope.launch { drawerState.close() }
          selectedItem.value = item
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
      )
    }
  }
   */

  //刷新数据
  codeViewModel.handIntent(CodeViewAction.Refresh)
}


@Composable
fun TestList() {
  val groups = remember { createGroupData() }
  Box(modifier = Modifier.fillMaxSize()) {
    CodeDrawerGroupListView(groups = groups)

  }
}


fun createGroupData(): List<Group> {
  return listOf(
    Group("Group 1", listOf("Item 1-1", "Item 1-2", "Item 1-3")),
    Group("Group 2", listOf("Item 2-1", "Item 2-2")),
    Group("Group 3", listOf("Item 3-1", "Item 3-2", "Item 3-3", "Item 3-4"))
  )
}