package com.ng.ngleetcode.model.code

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ng.base.BaseFragment
import com.ng.base.utils.LogUtil
import com.ng.code.util.ProblemAndroidUtil
import com.ng.code.util.model.CodeDataModel
import com.ng.code.util.tree.CodeNode
import com.ng.ngbaselib.utils.ViewUtils
import com.ng.ngleetcode.databinding.FragmentCodeBinding
import com.ng.ngleetcode.view.ToggleView.OnToggleListener
import com.ng.ngleetcode.view.adapter.NodeTreeAdapter
import com.ng.ngleetcode.view.code.CodeView.OnHighlightListener
import com.ng.ngleetcode.view.code.Language
import com.ng.ngleetcode.view.code.Theme

class CodeFragment : BaseFragment<CodeViewModel, FragmentCodeBinding>(), OnHighlightListener,
    OnToggleListener, NodeTreeAdapter.OnLeftItemClick {

    //左侧rv
    private lateinit var mLeftRvAdapter: NodeTreeAdapter

    //fab
    private var mFabDegree = 360F

    private var mNowData: CodeNode? = null

    companion object {
        fun getInstance(title: String): CodeFragment {
            val fragment = CodeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        mBinding.content.apply {
            setRadius(ViewUtils.dip2px(requireContext(), 5F))
        }
        mBinding.codeView
            .setOnHighlightListener(this)
            .setOnHighlightListener(this)
            .setTheme(Theme.ANDROIDSTUDIO)
            .setLanguage(Language.JAVA)
            .setWrapLine(false)
            .setFontSize(8F)
            .setZoomEnabled(true)
            .setShowLineNumber(false)
            .setStartLineNumber(0)
            .apply()
        showRandomProblem()

        //fab
        mBinding.fabRefresh.setOnClickListener {
            mBinding.fabRefresh.animate().rotation(mFabDegree).start()
            mFabDegree += 360f
            refreshRandomCode()
            //binding.appBarMain.toolbar.setTitle(mHomeFragment.refreshData())
        }

        //left
        mLeftRvAdapter = NodeTreeAdapter(this)
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
        mViewModel.refreshData()
    }

    override fun initObserve() {
        mViewModel.codeLiveData.observe(this) {
            mLeftRvAdapter.setList(it)
        }
    }

    /**
     * 随机显示一个题目
     */
    private fun refreshRandomCode() {


    }


    @SuppressLint("SetTextI18n")
    private fun refreshNowProgressBar() {
        ProblemAndroidUtil.getNowProgressAndroid(context)
        val allPro = ProblemAndroidUtil.codeNum
        val readPro = ProblemAndroidUtil.readNum
        mBinding.nowProgress.progress = readPro
        mBinding.nowProgress.max = allPro
        mBinding.tvProgress.text = "$readPro - $allPro"
    }

    private fun showRandomProblem(): String? {
        mNowData = ProblemAndroidUtil.getRandomProblemJavaContentNew(context)
        val refreshData = refreshData(mNowData)
        return mNowData?.contentPath?.split("/")?.toTypedArray()?.get(0)
    }

    fun refreshData(): String? {
        showAnim()
        return showRandomProblem()
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

    fun refreshData(codeBean: CodeNode?): String {
        mNowData = codeBean
        mNowData?.content = ProblemAndroidUtil.readAssets(context, mNowData?.contentPath)
        mBinding.codeView.setCode(mNowData?.content)?.apply()
        mNowData?.let { setTitle(it.title) }
        val state =
            CodeDataModel.getInstance().loopCodeState(requireActivity(), mNowData?.title, -1)
        LogUtil.d("当前:" + mNowData?.title + " state:" + state)
        refreshNowProgressBar()
        showAnim()
        return mNowData?.contentPath?.split("/")?.toTypedArray()?.get(0).toString()
    }

    fun setTitle(title: String) {
        mBinding.tvTitle.text = title.replace(".java", "")
    }

    override fun onStartCodeHighlight() {}

    override fun onFinishCodeHighlight() {}

    override fun onLanguageDetected(language: Language?, relevance: Int) {}

    override fun onFontSizeChanged(sizeInPx: Int) {}

    override fun onLineClicked(lineNumber: Int, content: String?) {}

    override fun onToggle(isPositive: Boolean) {
        if (activity != null) {
            CodeDataModel.getInstance()
                .loopCodeState(requireActivity(), mNowData!!.title, if (isPositive) 1 else 2)
            refreshNowProgressBar()
        }
    }

    /**
     * 左侧目录点击
     */
    override fun onItem(codeBean: CodeNode?) {

    }

}
