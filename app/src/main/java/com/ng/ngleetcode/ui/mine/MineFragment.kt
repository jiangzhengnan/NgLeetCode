package com.ng.ngleetcode.ui.mine


import android.os.Bundle
import android.view.View
import com.ng.ngbaselib.BaseFragment
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.R
import com.ng.ngleetcode.databinding.FragmentMineBinding


/**
 * des 我的
 * @author zs
 * @date 2020-05-14
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

    override fun getLayoutId(): Int = R.layout.fragment_info

    override fun initListener() {
        
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        
    }

    override fun onRetryBtnClick() {
        
    }

    override fun initData() {
        
    }
}
