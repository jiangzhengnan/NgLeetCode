package com.ng.ngleetcode.ui.page.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.ng.ngleetcode.R

sealed class BottomNavRoute(
  var routeName: String,
  @StringRes var stringId: Int,
  var icon: ImageVector
) {
  object Code : BottomNavRoute(RouteName.CODE, R.string.tab_1, Icons.Default.Home)
  object Statistics : BottomNavRoute(RouteName.STATISTICS, R.string.tab_2, Icons.Default.Star)
  object Read : BottomNavRoute(RouteName.READ, R.string.tab_3, Icons.Default.Menu)
  object Profile : BottomNavRoute(RouteName.PROFILE, R.string.tab_4, Icons.Default.Person)
}