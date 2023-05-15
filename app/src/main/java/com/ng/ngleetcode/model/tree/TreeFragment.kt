package com.ng.ngleetcode.model.tree


import android.os.Bundle
import android.view.View
import com.ng.base.BaseFragment
import com.ng.ngleetcode.app.EmptyViewModel
import com.ng.ngleetcode.databinding.FragmentTreeBinding


/**
 * todo des 广场 参考jetpack mvvm工程，广场
 * des 知识体系
 */
class TreeFragment : BaseFragment<EmptyViewModel, FragmentTreeBinding>() {

    companion object {
        fun getInstance(title: String): TreeFragment {
            val fragment = TreeFragment()
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
