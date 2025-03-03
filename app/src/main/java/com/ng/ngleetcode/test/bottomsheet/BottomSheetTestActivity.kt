package com.ng.ngleetcode.test.bottomsheet

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ng.ngleetcode.R

@RequiresApi(Build.VERSION_CODES.R)
class BottomSheetTestActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private var maxSheetHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomsheet_webview)

        initBottomSheet()
        initWebView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initBottomSheet() {
        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        // 配置底部弹窗行为
        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true // 跳过中间状态
            isFitToContents = false // 禁用自动适配内容高度
        }

        bottomSheetBehavior.isDraggable = false

        findViewById<View>(R.id.drag_handle).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 激活拖动效果
                    bottomSheetBehavior.isDraggable = true
                }
            }
            false
        }

    }

    private fun initWebView() {
        findViewById<View>(R.id.top_area).setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }


        findViewById<WebView>(R.id.webview).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    // 处理页面跳转
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // 激活拖动效果
                        bottomSheetBehavior.isDraggable = false
                    }
                }
                false
            }

            loadUrl("https://docs.qq.com/doc/DQnh5b1F4eFFsRGth?mode=editing&client=&AIGenerate=1&openAiAssistant=1")
        }
    }

    // 处理返回键
    override fun onBackPressed() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        } else {
            super.onBackPressed()
        }
    }
}