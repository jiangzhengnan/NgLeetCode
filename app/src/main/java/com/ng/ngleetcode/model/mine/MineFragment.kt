package com.ng.ngleetcode.model.mine


import android.os.Bundle
import android.view.View
import com.ng.base.BaseFragment
import com.ng.ngbaselib.utils.ViewUtils
import com.ng.ngleetcode.app.EmptyViewModel
import com.ng.ngleetcode.databinding.FragmentMineBinding


/**
 * 我的
 */
class MineFragment : BaseFragment<EmptyViewModel, FragmentMineBinding>() {

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        mBinding.userIcon.cornerRadius = ViewUtils.dpToPx(40F).toFloat()
        mBinding.llSetting.setOnClickListener {
            startTo(SettingActivity::class.java)
        }
    }

    override fun initData() {

    }

    override fun initObserve() {
    }
}
