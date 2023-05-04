package com.ng.ngleetcode.ui.home


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.R
import com.ng.ngleetcode.databinding.FragmentHomeKtBinding

class HomeKtFragment : BaseFragment<EmptyViewModel, FragmentHomeKtBinding>() {

    companion object {
        fun getInstance(title: String): HomeKtFragment {
            val fragment = HomeKtFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun createViewBinding(): FragmentHomeKtBinding =
        FragmentHomeKtBinding.inflate(layoutInflater)

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
