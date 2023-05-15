package com.ng.ngleetcode.model.info


import android.os.Bundle
import android.view.View
import com.ng.base.BaseFragment
import com.ng.ngleetcode.app.EmptyViewModel
import com.ng.ngleetcode.databinding.FragmentInfoBinding

/**
 * todo des 广场 参考jetpack mvvm工程，首页
 * 信息流式设计
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


    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        
    }

    override fun initData() {
        
    }

    override fun initObserve() {
    }
}
