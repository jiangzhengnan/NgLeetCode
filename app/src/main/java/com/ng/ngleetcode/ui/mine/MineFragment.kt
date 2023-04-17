package com.ng.ngleetcode.ui.mine


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.R
import com.ng.ngleetcode.databinding.FragmentMineBinding


/**
 * des 我的
 * @author zs
 * @date 2020-05-14
 */
class MineFragment : BaseFragment<EmptyViewModel, FragmentMineBinding>() {
    override fun createViewBinding(): FragmentMineBinding =
        FragmentMineBinding.inflate(layoutInflater)

    override fun createViewModel(): EmptyViewModel =
        ViewModelProvider(this, ViewModelFactory()).get(EmptyViewModel::class.java)


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
