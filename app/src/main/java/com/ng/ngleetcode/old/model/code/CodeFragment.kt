package com.ng.ngleetcode.old.model.code

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ng.base.BaseFragment
import com.ng.ngbaselib.utils.ViewUtils
import com.ng.ngleetcode.databinding.FragmentCodeBinding
import com.ng.ngleetcode.old.model.code.adapter.NodeTreeAdapter
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.data.ProblemRepository

class CodeFragment : BaseFragment<com.ng.ngleetcode.old.model.code.OldCodeViewModel, FragmentCodeBinding>(),
    NodeTreeAdapter.OnLeftItemClick {

    //左侧rv
    private lateinit var mLeftRvAdapter: NodeTreeAdapter

    //fab
    private var mFabDegree = 360F

    companion object {
        fun getInstance(title: String): com.ng.ngleetcode.old.model.code.CodeFragment {
            val fragment = com.ng.ngleetcode.old.model.code.CodeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    @SuppressLint("RtlHardcoded")
    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        mBinding.plAnim.setModel(mBinding.plAnim.SHOW_MODEL_SQUARE)

        mBinding.content.apply {
            setRadius(ViewUtils.dip2px(requireContext(), 5F))
        }
        mBinding.codeView
            .setTheme(com.ng.ngleetcode.old.model.code.view.Theme.ANDROIDSTUDIO)
            .setLanguage(com.ng.ngleetcode.old.model.code.view.Language.JAVA)
            .setWrapLine(false)
            .setFontSize(8F)
            .setZoomEnabled(true)
            .setShowLineNumber(false)
            .setStartLineNumber(0)
            .apply()

        //toolbar
        mBinding.toolbar.setNavigationOnClickListener {
            mBinding.drawer.openDrawer(Gravity.LEFT)
        }

        //fab
        mBinding.fabRefresh.setOnClickListener {
            mBinding.fabRefresh.animate().rotation(mFabDegree).start()
            mFabDegree += 360f
            showRandomCode()
        }
        mBinding.fabLeft.setOnClickListener {
            if (!mViewModel.showLeftCode()) {
                mViewModel.defUI.toastEvent.value = "没有了"
            }
        }
        mBinding.fabRight.setOnClickListener {
            if (!mViewModel.showRightCode()) {
                mViewModel.defUI.toastEvent.value = "没有了"
            }
        }
        mBinding.fabOk.setOnClickListener {
            mViewModel.toggleState()
        }

        //left
        mLeftRvAdapter =
            NodeTreeAdapter(this)
        mBinding.leftRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mLeftRvAdapter
        }

        //drawer
        mBinding.drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            @SuppressLint("NotifyDataSetChanged")
            override fun onDrawerOpened(drawerView: View) {
                mLeftRvAdapter.notifyDataSetChanged()
            }

            override fun onDrawerClosed(drawerView: View) {}

            override fun onDrawerStateChanged(newState: Int) {}
        })
    }

    override fun initData() {
        //刷新左侧菜单题库列表
        mViewModel.refreshDataList()
        //刷新当前题目，随机得到
        showRandomCode()
        //刷新进度
        mViewModel.refreshProgress()
    }

    @SuppressLint("SetTextI18n")
    override fun initObserve() {
        mViewModel.menuLiveData.observe(this) {
            mLeftRvAdapter.setList(it)
        }

        mViewModel.codeLiveData.observe(this) {
            it.content = ProblemRepository.readAssets(context, it.contentPath).toString()
            if (!mBinding.codeView.code.equals(it.content)) {
                mBinding.codeView.code = it.content
                mBinding.codeView.apply()
            }
            setCodeTitle(it.title, it.state == 1)
            setCodeMenu(it.getMenuStr().toString())
        }
        mViewModel.progressLiveData.observe(this) {
            mBinding.nowProgress.progress = it.nowPro
            mBinding.nowProgress.max = it.allPro
            mBinding.tvProgress.text = "${it.nowPro} - ${it.allPro}"
        }
    }

    private fun showRandomCode() {
        showAnim()
        mViewModel.refreshRandomCode()
    }

    private fun showAnim() {
        val pvhAlpha = PropertyValuesHolder.ofFloat(
            "alpha", 1.0f, 0f, 1.0f
        )
        val valueAnimator = ObjectAnimator
            .ofPropertyValuesHolder(mBinding.tvTitle, pvhAlpha)
        valueAnimator.start()
        startAnim(mBinding.codeView)
    }

    @SuppressLint("Recycle")
    private fun startAnim(target: View?) {
        if (target == null)
            return
        val animator2 = ObjectAnimator.ofFloat(target, "alpha", 1f, 0.7f, 1f)
        target.pivotX = target.measuredWidth.toFloat()
        target.pivotY = target.measuredHeight.toFloat()
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator2)
        animatorSet.start()
    }

    private fun setCodeTitle(title: String, isRead: Boolean) {
        mBinding.tvTitle.text = title.replace(".java", "")
        if (isRead) {
            mBinding.tvTitle.setTextColor(Color.GREEN)
        } else {
            mBinding.tvTitle.setTextColor(Color.BLACK)
        }
    }

    private fun setCodeMenu(title: String) {
        mBinding.tvMenu.text = title
    }

    /**
     * 左侧目录点击
     */
    override fun onItem(codeBean: CodeNode?) {
        mViewModel.refreshCode(codeBean)
        mBinding.drawer.closeDrawers()
    }

}
