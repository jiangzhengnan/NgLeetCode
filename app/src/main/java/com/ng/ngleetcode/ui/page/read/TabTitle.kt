package com.ng.ngleetcode.ui.page.read

data class TabTitle(
  val id: Int,
  val text: String,
  var cachePosition: Int = 0,
  var selected: Boolean = false
)