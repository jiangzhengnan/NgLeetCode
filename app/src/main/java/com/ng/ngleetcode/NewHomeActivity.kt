package com.ng.ngleetcode

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.ng.ngbaselib.BaseActivity
import com.ng.ngleetcode.databinding.ActivityHomeBinding
import com.ng.ngleetcode.ui.home.HomeKtFragment
import com.ng.ngleetcode.ui.info.InfoFragment
import com.ng.ngleetcode.ui.mine.MineFragment
import com.ng.ngleetcode.utils.UIUtil
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

class NewHomeActivity : BaseActivity<EmptyViewModel, ActivityHomeBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mNavigationController: NavigationController

    override fun initView(savedInstanceState: Bundle?) {

        // 禁止手势滑动
        mNavigationController =
            mBinding?.tabMain?.material()
                ?.addItem(
                    R.mipmap.index1_normal,
                    R.mipmap.index1_pressed,
                    UIUtil.getString(R.string.tab_1),
                    UIUtil.getColor(R.color.tab1_color)
                )
                ?.addItem(
                    R.mipmap.index2_normal,
                    R.mipmap.index2_pressed,
                    UIUtil.getString(R.string.tab_2),
                    UIUtil.getColor(R.color.tab2_color)
                )
                ?.addItem(
                    R.mipmap.index3_normal,
                    R.mipmap.index3_pressed,
                    UIUtil.getString(R.string.tab_3),
                    UIUtil.getColor(R.color.tab3_color)
                )
                ?.enableAnimateLayoutChanges()
                //.setDefaultColor(0x89FFFFFF.toInt())//未选中状态的颜色
                //.setMode(MaterialMode.CHANGE_BACKGROUND_COLOR or MaterialMode.HIDE_TEXT)
                ?.build()!!
        mBinding?.vpMain?.offscreenPageLimit = mNavigationController.itemCount
        mBinding?.vpMain?.adapter =
            MyViewPagerAdapter(supportFragmentManager, mNavigationController.itemCount)


        // 自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(mBinding?.vpMain!!)
        mNavigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
            }

            override fun onRepeat(index: Int) {
            }
        })


        //toolbar  暂时不需要左侧菜单栏
        //toolbar.title = UIUtil.getString(R.string.app_name)
        //setSupportActionBar(toolbar)
        //val toggle = ActionBarDrawerToggle(
        //    this,
        //    drawer_layout_main,
        //    toolbar,
        //    R.string.navigation_drawer_open,
        //    R.string.navigation_drawer_close
        //)
        //drawer_layout_main.addDrawerListener(toggle)
        //toggle.syncState()
        //nav_main.setNavigationItemSelectedListener(this)
    }

    override fun initData() {
//        viewModel.refreshResult.observe(this, Observer {
//        })
//        viewModel.errorResult.observe(this, Observer {
//            showToast(getString(R.string.token_need_login))
//            UserManager.getInstance().clearUserInfo()
//            JumpManager.jumpToLogin()
//        })
//        viewModel.refreshToken()
    }


    class MyViewPagerAdapter(fm: FragmentManager, private val size: Int) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeKtFragment.getInstance(UIUtil.getString(R.string.tab_1))
                1 -> InfoFragment.getInstance(UIUtil.getString(R.string.tab_2))
                2 -> MineFragment.getInstance(UIUtil.getString(R.string.tab_3))
                else -> MineFragment.getInstance(UIUtil.getString(R.string.tab_3))
            }
        }

        override fun getCount(): Int {
            return size
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // when (item.itemId) {
        return true
    }

    //clazz传入fragment类：如：HomeFragment.class
    fun getFragment(clazz: Class<*>?): Fragment? {
        val fragments = supportFragmentManager.fragments
        if (fragments.size > 0) {
            val navHostFragment = fragments[0] as NavHostFragment
            val childFragments = navHostFragment.childFragmentManager
                .fragments
            if (childFragments.size > 0) {
                for (j in childFragments.indices) {
                    val fragment = childFragments[j]
                    if (fragment.javaClass.isAssignableFrom(clazz)) {
                        return fragment
                    }
                }
            }
        }
        return null
    }


    override fun onRetryBtnClick() {

    }
}