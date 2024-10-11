package com.ng.ngleetcode.ui.page.profile

import androidx.lifecycle.ViewModel
import com.ng.base.utils.SPreference
import com.ng.ngleetcode.old.constants.SpConstants

class ProfileViewModel : ViewModel() {

  private var mSpCodeStateListJsonStr: String by SPreference(SpConstants.CODE_STATE, "")

  fun handIntent(action: ProfileViewAction) {
    when (action) {
      is ProfileViewAction.ClearCache -> {
        mSpCodeStateListJsonStr = ""
        //Snackbar.make(mBinding.root, "清除成功", Snackbar.LENGTH_SHORT).show();
      }
    }
  }

}

//data class ProfileViewState(
//  val showClearCachePop: Boolean = false
//)

sealed class ProfileViewAction {
  object ClearCache : ProfileViewAction() // 清除本地缓存
}