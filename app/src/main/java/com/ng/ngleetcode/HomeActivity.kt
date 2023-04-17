package com.ng.ngleetcode

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ng.ngbaselib.BaseActivity
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngleetcode.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    override fun createViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun createViewModel(): EmptyViewModel =
        ViewModelProvider(this, ViewModelFactory()).get(EmptyViewModel::class.java)

    override fun layoutId(): Int = R.layout.activity_home

    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun onRetryBtnClick() {
        TODO("Not yet implemented")
    }
}