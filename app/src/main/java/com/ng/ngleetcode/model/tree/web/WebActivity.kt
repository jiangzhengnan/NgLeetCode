package com.ng.ngleetcode.model.tree.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import com.ng.base.BaseActivity
import com.ng.base.utils.MLog
import com.ng.base.utils.Param
import com.ng.ngleetcode.databinding.ActivityWebBinding

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/15
 *    @description   :
 */
class WebActivity : BaseActivity<WebViewModel, ActivityWebBinding>() {
    /**
     * 通过注解接收参数
     * url
     */
    @Param
    private var loadUrl: String? = null

    /**
     * 文章标题
     */
    @Param
    private var title: String? = null

    /**
     * 文章id
     */
    @Param
    private var id: Int? = -1

    /**
     * 作者
     */
    @Param
    private var author: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.tvTitle.text = title
        mBinding.ivBack.setOnClickListener {
            finish()
        }
        val webSettings: WebSettings = mBinding.wbView.settings
        webSettings.javaScriptEnabled = true
        //自适应屏幕
        mBinding.wbView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mBinding.wbView.settings.loadWithOverviewMode = true

        //如果不设置WebViewClient，请求会跳转系统浏览器
        mBinding.wbView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址）
                //均交给webView自己处理，这也是此方法的默认处理
                return false
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址）
                //均交给webView自己处理，这也是此方法的默认处理
                return false
            }
        }

        loadUrl?.let {
            mBinding.wbView.loadUrl(it)
        }

        //设置最大进度
        mBinding.progress.max = 100
        //webView加载成功回调
        mBinding.wbView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                //进度小于100，显示进度条
                if (newProgress < 100) {
                    mBinding.progress.visibility = View.VISIBLE
                }
                //等于100隐藏
                else if (newProgress == 100) {
                    mBinding.progress.visibility = View.GONE
                }
                //改变进度
                mBinding.progress.progress = newProgress
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //自定义返回
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (mBinding.wbView.canGoBack()) {
                        //返回上个页面
                        mBinding.wbView.goBack()
                    } else {
                        //退出H5界面
                        finish()
                    }

                }
            }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun initObserve() {
    }

    override fun initData() {
        MLog.d("接受参数: $loadUrl $title $id $author")
    }


}