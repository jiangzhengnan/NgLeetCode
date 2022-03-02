package com.ng.ngleetcode;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ng.ngleetcode.util.ProgressUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    TextView mShowTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowTv = findViewById(R.id.tv_show);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String proLink = ProgressUtil.getRandomProblemLink();
            }
        });
        mShowTv.setText(getFromAssets("StartCode.java"));
    }

    public String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null) {
                Result += line;
                Result += "\n";
            }

            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
