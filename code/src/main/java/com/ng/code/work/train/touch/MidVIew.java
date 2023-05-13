package com.ng.code.work.train.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.ng.base.utils.LogUtil;

public class MidVIew extends RelativeLayout {
    private final static String TAG = "MidVIew";

    public MidVIew(Context context) {
        super(context);
    }

    public MidVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MidVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MidVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(true);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "onTouchEvent - actionDown");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "onTouchEvent - actionMove");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "onTouchEvent - actionUp");
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
