package com.ng.ngleetcode.old

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.ng.base.BaseActivity
import com.ng.ngleetcode.R
import com.ng.ngleetcode.old.app.EmptyViewModel
import com.ng.ngleetcode.databinding.ActivityHomeBinding
import com.ng.ngleetcode.old.model.mine.MineFragment
import com.ng.ngleetcode.old.model.tree.TreeFragment
import com.ng.ngleetcode.old.utils.UIUtil
import me.majiajie.pagerbottomtabstrip.MaterialMode
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

class HomeActivity : BaseActivity<EmptyViewModel, ActivityHomeBinding>(),
    NavigationView.OnNavigationItemSelectedListener, OnTabItemSelectedListener {

    private lateinit var mNavigationController: NavigationController

    companion object {
        val mItemColors = arrayListOf(
            UIUtil.getColor(R.color.tab1_color),
            UIUtil.getColor(R.color.tab2_color),
            UIUtil.getColor(R.color.tab3_color),
        )

        val mItemIcons = arrayListOf(
          R.mipmap.ic_book_black_24dp,
          R.mipmap.ic_baseline_account_tree_24,
          R.mipmap.ic_baseline_person_pin_24,
        )

        val mItemTitles = arrayListOf(
            UIUtil.getString(R.string.tab_1),
            UIUtil.getString(R.string.tab_2),
            UIUtil.getString(R.string.tab_3),
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        mNavigationController = mBinding.tabMain.material()
            .addItem(
                mItemIcons[0],
                mItemTitles[0],
                mItemColors[0]
            )
            .addItem(
                mItemIcons[1],
                mItemTitles[1],
                mItemColors[1]
            )
            .addItem(
                mItemIcons[2],
                mItemTitles[2],
                mItemColors[2]
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

    }

    override fun initObserve() {
    }

    override fun initData() {
    }

    class MyViewPagerAdapter(fm: FragmentManager, private val size: Int) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> com.ng.ngleetcode.old.model.code.CodeFragment.getInstance(mItemTitles[position])
                1 -> TreeFragment.getInstance(mItemTitles[position])
                2 -> MineFragment.getInstance(mItemTitles[position])
                else -> MineFragment.getInstance(mItemTitles[position])
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
        window.statusBarColor = mItemColors[index]
    }

    override fun onRepeat(index: Int) {
    }
}