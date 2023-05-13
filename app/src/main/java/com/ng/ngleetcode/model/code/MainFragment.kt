package com.ng.ngleetcode.model.code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ng.base.BaseFragment
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.databinding.FragmentMainBinding
import com.ng.ngleetcode.model.info.InfoFragment
import com.ng.ngleetcode.model.mine.MineFragment

class MainFragment : BaseFragment<EmptyViewModel, FragmentMainBinding>() {
    private val fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }


    /**
     * 咨询
     */
    private val infoFragment by lazy { InfoFragment() }


    /**
     * 我的
     */
    private val mineFragment by lazy { MineFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(infoFragment)
            add(mineFragment)
        }
    }


    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
//        //初始化viewpager2
//        mBinding?.vpHome?.initFragment(childFragmentManager, fragmentList)?.run {
//            //全部缓存,避免切换回重新加载
//            offscreenPageLimit = fragmentList.size
//        }
//
//        mBinding?.vpHome?.doSelected {
//            mBinding?.btnNav?.menu?.getItem(it)?.isChecked = true
//        }
//        //初始化底部导航栏
//        mBinding?.btnNav?.run {
//            setOnNavigationItemSelectedListener { item ->
//                when (item.itemId) {
//                    R.id.menu_home -> {
//                        mBinding?.vpHome?.setCurrentItem(0, false)
//                    }
//                    R.id.menu_project -> mBinding?.vpHome?.setCurrentItem(1, false)
//                    R.id.menu_square -> mBinding?.vpHome?.setCurrentItem(2, false)
//                    R.id.menu_mine -> mBinding?.vpHome?.setCurrentItem(4, false)
//                }
//                // 这里注意返回true,否则点击失效
//                true
//            }
//        }
    }

    override fun initData() {
        
    }

    override fun initObserve() {
    }


}