package com.ng.ngleetcode;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ng.ngleetcode.android.CodeBean;
import com.ng.ngleetcode.android.NgTextView;
import com.ng.ngleetcode.util.ProblemAndroidUtil;

public class MainActivity extends Activity {
    TextView mTitleView;
    NgTextView mShowTv;
    ImageView mStartBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowTv = findViewById(R.id.tv_show);
        mTitleView = findViewById(R.id.tv_title);
        mStartBtn = findViewById(R.id.btn_start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            float degree = 180;

            @Override
            public void onClick(View view) {
                mStartBtn.animate().rotation(degree).setDuration(500).start();
                degree += 180;
                showRandomProblem();
            }
        });
        mShowTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        showRandomProblem();
    }

    private void showRandomProblem() {
        CodeBean codeBean = ProblemAndroidUtil.getRandomProblemJavaContent(this);
        mShowTv.setText(codeBean.content);
        mShowTv.scrollTo(0, 0);
        mTitleView.setText(codeBean.title);
    }


}
