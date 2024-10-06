package com.ng.ngleetcode.ui.page.code.mvi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ng.base.BaseViewModel
import com.ng.ngleetcode.MyApp
import com.ng.ngleetcode.old.model.code.bean.CodeDirNode

class CodeViewModel(private val codeModel: CodeModel) : BaseViewModel(MyApp.instance) {

  private val _codeDrawerListState = mutableStateOf(CodeDrawerViewState())

  // 题库列表数据
  val codeDrawerListState: State<CodeDrawerViewState> = _codeDrawerListState

  // 唯一入口方法
  fun handIntent(intent: CodeViewAction) {
    when (intent) {
      is CodeViewAction.Refresh -> {
        val codeList: List<CodeDirNode?> = codeModel.getDataList()
        val progressData: Array<IntArray> = codeModel.getProgressData()


        _codeDrawerListState.value = CodeDrawerViewState(
          codeListData = codeList,
          easyRead = progressData[0][0], easyCount = progressData[0][1],
          midRead = progressData[1][0], midCount = progressData[1][1],
          hardRead = progressData[2][0], hardCount = progressData[2][1],
          nowPro = progressData[3][0], allPro = progressData[3][1]
        )
      }
      else -> {}
    }
  }

}

class CodeViewModelFactory(private val codeModel: CodeModel) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CodeViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return CodeViewModel(codeModel) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}

// 题库列表数据
data class CodeDrawerViewState(
  val codeListData: List<CodeDirNode?>? = null,
  val easyRead: Int = 0,
  val easyCount: Int = 0,
  val midRead: Int = 0,
  val midCount: Int = 0,
  val hardRead: Int = 0,
  val hardCount: Int = 0,
  val nowPro: Int = 0,
  val allPro: Int = 1,

  )

// 用户行为
sealed class CodeViewAction {
  object Refresh : CodeViewAction()
}