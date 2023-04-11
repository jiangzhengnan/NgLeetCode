package com.ng.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import com.ng.base.R;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/04/11
 * @description :
 */
public class TestAty extends Activity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
