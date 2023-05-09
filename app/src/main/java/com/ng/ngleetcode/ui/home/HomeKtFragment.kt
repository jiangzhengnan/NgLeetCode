package com.ng.ngleetcode.ui.home


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.ng.base.utils.LogUtil
import com.ng.code.util.ProblemAndroidUtil
import com.ng.code.util.model.CodeDataModel
import com.ng.code.util.tree.CodeNode
import com.ng.ngbaselib.BaseFragment
import com.ng.ngleetcode.EmptyViewModel
import com.ng.ngleetcode.R
import com.ng.ngleetcode.databinding.FragmentHomeKtBinding
import com.ng.ngleetcode.view.ToggleView.OnToggleListener
import com.ng.ngleetcode.view.code.CodeView.OnHighlightListener
import com.ng.ngleetcode.view.code.Language
import com.ng.ngleetcode.view.code.Theme

class HomeKtFragment : BaseFragment<EmptyViewModel, FragmentHomeKtBinding>(), OnHighlightListener,
    OnToggleListener {
    private var mNowData: CodeNode? = null

    companion object {
        fun getInstance(title: String): HomeKtFragment {
            val fragment = HomeKtFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun getLayoutId(): Int = R.layout.fragment_info

    override fun initListener() {

    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        // binding.codeContent.setRadius(UIUtil.INSTANCE.dp2px(getContext(), 5));
        // binding.codeContent.setRadius(UIUtil.INSTANCE.dp2px(getContext(), 5));
        mBinding?.toggleCode?.setOnToggleListener(this)
        mBinding?.codeView?.setOnHighlightListener(this)
            ?.setOnHighlightListener(this)
            ?.setTheme(Theme.ANDROIDSTUDIO)
            ?.setLanguage(Language.JAVA)
            ?.setWrapLine(false)
            ?.setFontSize(8F)
            ?.setZoomEnabled(true)
            ?.setShowLineNumber(false)
            ?.setStartLineNumber(0)
            ?.apply()
        showRandomProblem()
    }

    override fun onRetryBtnClick() {

    }

    override fun initData() {

    }


    private fun refreshNowProgressBar() {
        ProblemAndroidUtil.getNowProgressAndroid(context)
        val allPro = ProblemAndroidUtil.codeNum
        val readPro = ProblemAndroidUtil.readNum
        mBinding?.nowPb?.progress = readPro
        mBinding?.nowPb?.max = allPro
        mBinding?.tvPb?.text = "$readPro - $allPro"
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
            .ofPropertyValuesHolder(mBinding?.tvTitle, pvhAlpha)
        valueAnimator.start()
        startAnim(mBinding?.codeView)
    }

    @SuppressLint("Recycle")
    private fun startAnim(target: View?) {
        if (target==null)
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
        mNowData?.content = ProblemAndroidUtil.readAssets(context, mNowData?.contentPath   )
        mBinding?.codeView?.setCode(mNowData?.content)?.apply()
        mNowData?.let { setTitle(it.title) }
        val state =
            CodeDataModel.getInstance().loopCodeState(requireActivity(), mNowData?.title, -1)
        LogUtil.d("当前:" + mNowData?.title + " state:" + state)
        refreshNowProgressBar()
        mBinding?.toggleCode?.setPositive(state != 2)
        showAnim()
        return mNowData?.contentPath?.split("/")?.toTypedArray()?.get(0).toString()
    }

    fun setTitle(title: String) {
        mBinding?.tvTitle?.text = title.replace(".java", "")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
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

}
