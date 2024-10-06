package com.ng.ngleetcode.old.model.mine

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.ng.base.BaseActivity
import com.ng.base.utils.SPreference
import com.ng.ngleetcode.old.app.EmptyViewModel
import com.ng.ngleetcode.old.constants.SpConstants
import com.ng.ngleetcode.databinding.ActivitySettingBinding

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/20
 *    @description   :
 */
class SettingActivity : BaseActivity<EmptyViewModel, ActivitySettingBinding>() {

    private var mSpCodeStateListJsonStr: String by SPreference(SpConstants.CODE_STATE, "")

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.ivBack.setOnClickListener {
            finish()
        }

        mBinding.tvClear.setOnClickListener {
            mSpCodeStateListJsonStr = ""
            Snackbar.make(mBinding.root, "清除成功", Snackbar.LENGTH_SHORT).show();
        }
    }

    override fun initObserve() {
    }

    override fun initData() {
    }

}