package com.ng.ngleetcode.ui.page.code.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Group(
  val title: String,
  val children: List<String>
) {
  val expandedState = mutableStateOf(false)
}

@Composable
fun ExpandableGroup(group: Group, index: Int) {
  val toggleExpanded: () -> Unit = { group.expandedState.value = !group.expandedState.value }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = group.title,
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp),
      fontSize = 16.sp
    )
    IconButton(onClick = toggleExpanded) {
      Icon(
        imageVector = if (group.expandedState.value) Icons.Default.ArrowDropDown else Icons
          .Default.ArrowBack,
        contentDescription = "Expand or collapse group"
      )
    }
  }
}

@Composable
fun ExpandableItems(items: List<String>, expanded: Boolean) {
  if (expanded) {
    Column {
      items.forEach { item ->
        Text(
          text = item,
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        )
      }
    }
  }
}

@Composable
fun CodeDrawerGroupListView(groups: List<Group>) {
  LazyColumn {
    items(
      items = groups,
      key = { group -> group.title }
    ) { group ->
      ExpandableGroup(group = group, index = 0)
      ExpandableItems(items = group.children, expanded = group.expandedState.value)
    }
  }
}