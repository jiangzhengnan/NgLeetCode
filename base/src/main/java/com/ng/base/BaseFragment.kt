package com.ng.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.ng.base.event.Message
import com.ng.base.fragment.FragmentUserVisibleController
import com.ng.base.utils.BindingUtil
import com.ng.base.utils.ColorUtil
import com.ng.base.utils.ParamUtil
import com.ng.base.utils.ToastUtils
import com.ng.base.view.StateLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * base:
 * 各种状态展示layout
 * 各种状态展示dialog
 *
 * 方法：
 * setStatusColor 设置状态栏颜色
 *
 * @author Jzn
 * @date 2020-06-12
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment(),
    FragmentUserVisibleController.UserVisibleCallback {
    var mTitle: String? = null

    //是否需要Loading布局
    private fun isNeedLoad(): Boolean {
        return true
    }

    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VB

    //是否第一次加载
    private var isFirst: Boolean = true
    private var dialog: MaterialDialog? = null

    //各种状态展示layout
    private var mLayoutId = 0
    private var mStateLayout: StateLayout? = null
    private var mContentFrameLayout: FrameLayout? = null


    private var mCustomView: View? = null

    private var mContentView: View? = null

    private var mRootView: View? = null

    private fun <VB> createVb(): VB {
        val type = this.javaClass.genericSuperclass
        // 获取 所包含的泛型参数列表
        val types: Array<Type> = (type as ParameterizedType).actualTypeArguments
        val viewBindClass = types[1] as Class<out ViewBinding?>
        return BindingUtil.createViewBinding(viewBindClass, layoutInflater)!!
    }

    private fun viewModelClass(): Class<VM> {
        val type = this.javaClass.genericSuperclass
        val types: Array<Type> = (type as ParameterizedType).actualTypeArguments
        return types[0] as Class<VM>
    }

    private var mUserVisibleController: FragmentUserVisibleController? = null

    init {
        mUserVisibleController = FragmentUserVisibleController(this, this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ParamUtil.initParam(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //ARouter.getInstance().inject(this)
        //绑定多状态layout
        if (mContentView == null) {
            mContentView = createRootView(inflater, container, savedInstanceState)
        } else {
            val parent = mContentView?.parent
            if (parent != null && parent is ViewGroup) {
                parent.removeView(mContentView)
            }
        }
        EventBus.getDefault().let {
            if (!it.isRegistered(this)) {
                it.register(this)
            }
        }
        return mContentView
    }

    open fun createRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = createVb()

        mViewModel = ViewModelProvider(this, ViewModelFactory()).get(viewModelClass())
        lifecycle.addObserver(mViewModel)
        //注册 UI事件
        registorDefUIChange()

        mCustomView = (mBinding as ViewBinding).root;

        if (isNeedLoad()) {
            val basicView =
                LayoutInflater.from(activity).inflate(R.layout.activity_basic, null, false);
            mStateLayout = basicView.findViewById<View>(R.id.loading_layout) as StateLayout
            mContentFrameLayout = basicView.findViewById<View>(R.id.content_layout) as FrameLayout
            mContentFrameLayout!!.addView(mCustomView)
            setRoot(basicView)
        } else {
            setRoot(mCustomView!!)
        }
        return getRoot()
    }

    @Subscribe
    open fun onGetMessage(t: Message) {
    }


    protected open fun getRoot(): View? {
        return this.mRootView
    }

    protected open fun setRoot(view: View) {
        this.mRootView = view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(mViewModel)
        //注册 UI事件
        registorDefUIChange()
        initViewsAndEvents(mContentView, savedInstanceState)
        initObserve()
        initData()

    }


    /**
     * 是否和 Activity 共享 ViewModel,默认不共享
     * Fragment 要和宿主 Activity 的泛型是同一个 ViewModel
     */
    open fun isShareVM(): Boolean = false


    override fun onResume() {
        super.onResume()
        mUserVisibleController!!.resume()
    }

    override fun onPause() {
        super.onPause()
        mUserVisibleController!!.pause()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mUserVisibleController!!.activityCreated()
    }

    abstract fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?)

    protected fun onRetryBtnClick(){

    }

    abstract fun initData()

    abstract fun initObserve()

    open fun showLoadingLayout(msg: String?) {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showLoading(msg)
        }
    }

    open fun showEmptyLayout() {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showEmpty()
        }
    }

    open fun showLoadingErrorLayout(msg: String?) {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showError(msg)
            mStateLayout?.setRetryClickListener(View.OnClickListener { onRetryBtnClick() })
        }
    }


    open fun showContentLayout() {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.VISIBLE
        }
        if (mStateLayout != null) {
            mStateLayout?.visibility = View.GONE
            mStateLayout?.pauseAnimation()
        }
    }

    /**
     * 设置状态栏颜色
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun setStatusColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            requireActivity().window.statusBarColor =
                if (color == 0) ColorUtil.getColor(
                    requireActivity(),
                    R.color.colorPrimaryDark
                ) else color
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // 跟随系统
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    open fun <T : View?> findViewById(id: Int): T? {
        return if (mRootView == null) null else mRootView?.findViewById<View>(id) as T
    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        mViewModel.defUI.showDialog.observe(viewLifecycleOwner, Observer {
            showLoading()
        })
        mViewModel.defUI.showEmpty.observe(viewLifecycleOwner, Observer {
            showEmptyLayout()
        })
        mViewModel.defUI.showError.observe(viewLifecycleOwner, Observer {
            showLoadingErrorLayout(getString(R.string.loading_fail))
        })
        mViewModel.defUI.dismissDialog.observe(viewLifecycleOwner, Observer {
            dismissLoading()
        })
        mViewModel.defUI.toastEvent.observe(viewLifecycleOwner, Observer {
            ToastUtils.showShort(context, it)
        })
        mViewModel.defUI.msgEvent.observe(viewLifecycleOwner, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}


    /**
     * 打开等待框
     */
    protected fun showLoading() {
        if (dialog == null) {
            dialog = context?.let {
                MaterialDialog(it)
                    .cancelable(true)
                    .cornerRadius(8f)
                    .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                    .lifecycleOwner(this)
                    .maxWidth(R.dimen.dialog_width)
            }
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

    fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    open fun startTo(targetClass: Class<out Activity>) {
        val intent = Intent(activity, targetClass)
        startActivity(intent)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    /**
     * 监听界面是否展示给用户，实现懒加载
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mUserVisibleController!!.setUserVisibleHint(isVisibleToUser)
    }

    override fun isWaitingShowToUser(): Boolean {
        return mUserVisibleController!!.isWaitingShowToUser
    }

    override fun setWaitingShowToUser(waitingShowToUser: Boolean) {
        mUserVisibleController!!.isWaitingShowToUser = waitingShowToUser
    }

    override fun isVisibleToUser(): Boolean {
        return mUserVisibleController!!.isVisibleToUser
    }

    override fun callSuperSetUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    private var isFirstVisible = true

    private var isFirstInvisible = true

    private var isRealVisible = false

    override fun onVisibleToUserChanged(
        isVisibleToUser: Boolean,
        invokeInResumeOrPause: Boolean
    ) {
        if (isVisibleToUser) {
            if (!isRealVisible) {
                if (isFirstVisible) {
                    isFirstVisible = false
                    onUserFirstVisible()
                } else {
                    onUserVisible()
                }
                isRealVisible = true
            }
        } else {
            if (isRealVisible) {
                if (isFirstInvisible) {
                    isFirstInvisible = false
                    onUserFirstInvisible()
                } else {
                    onUserInvisible()
                }
                isRealVisible = false
            }
        }
    }

    /**
     * 用户可见，第一次可见不调用
     */
    open fun onUserVisible() {}

    /**
     * 用户不可见，第一次不可见不调用
     */
    open fun onUserInvisible() {}

    /**
     * 用户第一次可见
     */
    open fun onUserFirstVisible() {
    }

    /**
     * 用户第一次不可见
     */
    open fun onUserFirstInvisible() {}


    fun setText(tv: TextView, str: String?) {
        if (!str.isNullOrEmpty()) {
            tv.text = str
        } else {
            tv.text = "--"
        }
    }

}