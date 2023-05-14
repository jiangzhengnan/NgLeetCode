package com.ng.ngleetcode

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.ng.base.BaseActivity
import com.ng.ngleetcode.databinding.ActivityHomeBinding
import com.ng.ngleetcode.model.code.CodeFragment
import com.ng.ngleetcode.model.info.InfoFragment
import com.ng.ngleetcode.model.mine.MineFragment
import com.ng.ngleetcode.utils.UIUtil
import me.majiajie.pagerbottomtabstrip.MaterialMode
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

class HomeActivity : BaseActivity<EmptyViewModel, ActivityHomeBinding>(),
    NavigationView.OnNavigationItemSelectedListener, OnTabItemSelectedListener {

    private lateinit var mNavigationController: NavigationController

    override fun initView(savedInstanceState: Bundle?) {
        mNavigationController = mBinding.tabMain.material()
            .addItem(
                R.mipmap.ic_book_black_24dp,
                UIUtil.getString(R.string.tab_1),
                UIUtil.getColor(R.color.tab1_color)
            )
            .addItem(
                R.mipmap.ic_baseline_account_tree_24,
                UIUtil.getString(R.string.tab_2),
                UIUtil.getColor(R.color.tab2_color)
            )
            .addItem(
                R.mipmap.ic_baseline_newspaper_24,
                UIUtil.getString(R.string.tab_3),
                UIUtil.getColor(R.color.tab3_color)
            )
            .addItem(
                R.mipmap.ic_baseline_person_pin_24,
                UIUtil.getString(R.string.tab_4),
                UIUtil.getColor(R.color.tab4_color)
            )
            .enableAnimateLayoutChanges()
            .setDefaultColor(0x89FFFFFF.toInt())//未选中状态的颜色
            .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR or MaterialMode.HIDE_TEXT)
            .build()
        mBinding.vpMain.apply {
            offscreenPageLimit = mNavigationController.itemCount
            adapter = MyViewPagerAdapter(supportFragmentManager, mNavigationController.itemCount)
        }
        mNavigationController.apply {
            setupWithViewPager(mBinding.vpMain)
            addTabItemSelectedListener(this@HomeActivity)

        }

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
                0 -> CodeFragment.getInstance(UIUtil.getString(R.string.tab_1))
                1 -> InfoFragment.getInstance(UIUtil.getString(R.string.tab_2))
                2 -> InfoFragment.getInstance(UIUtil.getString(R.string.tab_3))
                3 -> MineFragment.getInstance(UIUtil.getString(R.string.tab_4))
                else -> MineFragment.getInstance(UIUtil.getString(R.string.tab_3))
            }
        }

        override fun getCount(): Int {
            return size
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }


    override fun onSelected(index: Int, old: Int) {
    }

    override fun onRepeat(index: Int) {
    }
}