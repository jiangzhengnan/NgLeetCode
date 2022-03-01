package com.ng.ngleetcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ng.ngleetcode.util.ProgressUtil;

public class MainActivity extends Activity {

    WebView mWebMain;
    TextView mShowTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebMain = findViewById(R.id.web_main);
        mShowTv = findViewById(R.id.tv_show);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proLink = ProgressUtil.getRandomProblemLink();
                mShowTv.setText(proLink);
                mWebMain.loadUrl(proLink);
            }
        });
        mWebMain.loadUrl("https://www.baidu.com/");

        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        mWebMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用WebView加载显示url
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }
}
