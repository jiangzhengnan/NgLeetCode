package com.ng.ngleetcode.train.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.Nullable;

import com.ng.base.LogUtil;

public class LittleView extends View {
    private final static String TAG = "LittleView";

    public LittleView(Context context) {
        super(context);
    }

    public LittleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LittleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LittleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "dispatchTouchEvent - actionDown");
                return true;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "dispatchTouchEvent - actionMove");

                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "dispatchTouchEvent - actionUp");
                break;

        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestParentDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "onTouchEvent - actionDown");
                return true;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "onTouchEvent - actionMove");

                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "onTouchEvent - actionUp");
                break;

        }

        return super.onTouchEvent(event);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

}
