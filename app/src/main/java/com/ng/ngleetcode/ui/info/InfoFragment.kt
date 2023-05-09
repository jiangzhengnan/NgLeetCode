package com.ng.ngleetcode.ui.info


import android.os.Bundle
import android.view.View
import com.ng.ngbaselib.BaseFragment
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.R
import com.ng.ngleetcode.databinding.FragmentInfoBinding


/**
 * des 我的
 * @author zs
 * @date 2020-05-14
 */
class InfoFragment : BaseFragment<EmptyViewModel, FragmentInfoBinding>() {

    companion object {
        fun getInstance(title: String): InfoFragment {
            val fragment = InfoFragment()
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
