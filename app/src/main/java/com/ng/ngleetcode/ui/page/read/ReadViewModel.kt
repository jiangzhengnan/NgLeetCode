package com.ng.ngleetcode.ui.page.read

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ReadViewModel : ViewModel() {

  val titles by mutableStateOf(
    mutableListOf(
      TabTitle(201, "体系"),
      TabTitle(202, "导航"),
      TabTitle(203, "公众号"),
    )
  )

  override fun onCleared() {
    super.onCleared()
    println("ReadViewModel ==> onClear")
  }

}