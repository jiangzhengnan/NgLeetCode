package com.ng.ngbaselib

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.ng.base.BaseViewModel
import com.ng.base.R
import com.ng.base.event.Message
import com.ng.base.utils.BindingUtil
import com.ng.base.utils.ToastUtils
import com.ng.base.view.StateLayout
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * 增加不同状态view todo
 *
 *
 * @author Jzn
 * @date 2020/6/19
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    protected var mViewModel: VM? = null

    protected var mBinding: VB? = null

    private fun <VB> createVb(): VB? {
        val type = this.javaClass.genericSuperclass ?: return null
        // 获取 所包含的泛型参数列表
        val types: Array<Type> = (type as ParameterizedType).actualTypeArguments
        val viewBindClass = types[1] as Class<out ViewBinding?>
        return BindingUtil.createViewBinding(viewBindClass, layoutInflater)
    }

    private fun viewModelClass(): Class<VM> {
        val type = this.javaClass.genericSuperclass
        val types: Array<Type> = (type as ParameterizedType).actualTypeArguments
        return types[0] as Class<VM>
    }

    private var dialog: MaterialDialog? = null
    private var mCustomView: View? = null
    private var mLayoutId = 0
    private var mStateLayout: StateLayout? = null
    private var mContentFrameLayout: FrameLayout? = null

    //是否需要Loading布局
    protected fun isNeedLoad(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = createVb()
        if (mBinding == null) {
            throw java.lang.NullPointerException("mBinding is null")
        }

        mViewModel = ViewModelProvider(this, ViewModelFactory()).get(viewModelClass())
        if (mViewModel != null) {
            lifecycle.addObserver(mViewModel!!)
            //注册 UI事件
            registorDefUIChange()
        }

        mCustomView = (mBinding as ViewBinding).root;

        if (isNeedLoad()) {
            val basicView = LayoutInflater.from(this).inflate(R.layout.activity_basic, null, false);
            mStateLayout = basicView.findViewById<View>(R.id.loading_layout) as StateLayout
            mContentFrameLayout = basicView.findViewById<View>(R.id.content_layout) as FrameLayout
            mContentFrameLayout!!.addView(mCustomView)
            setContentView(basicView)
        } else {
            setContentView(mCustomView)
        }

        initView(savedInstanceState)
        initData()
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        if (mViewModel != null) {
            mViewModel!!.defUI.showDialog.observe(this, Observer {
                showLoading()
            })
            mViewModel!!.defUI.dismissDialog.observe(this, Observer {
                dismissLoading()
            })
            mViewModel!!.defUI.toastEvent.observe(this, Observer {
                ToastUtils.showShort(baseContext, it)
            })
            mViewModel!!.defUI.msgEvent.observe(this, Observer {
                handleEvent(it)
            })
        }
    }

    open fun handleEvent(msg: Message) {}

    abstract fun onRetryBtnClick()

    /**
     * 打开等待框
     */
    private fun showLoading() {
        if (dialog == null) {
            dialog = MaterialDialog(this)
                .cancelable(false)
                .cornerRadius(8f)
                .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                .lifecycleOwner(this)
                .maxWidth(R.dimen.dialog_width)
        }
        dialog?.show()

    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }


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

    open fun startTo(targetClass: Class<out Activity>) {
        val intent = Intent(this, targetClass)
        startActivity(intent)
    }

}