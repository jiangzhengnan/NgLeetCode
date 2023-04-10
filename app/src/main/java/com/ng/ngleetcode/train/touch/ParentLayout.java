package com.ng.ngleetcode.train.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ng.base.utils.LogUtil;

public class ParentLayout extends RelativeLayout {
    private final static String TAG = "ParentLayout";

    public ParentLayout(@NonNull Context context) {
        super(context);
    }

    public ParentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ParentLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "dispatchTouchEvent - actionDown");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "dispatchTouchEvent - actionMove"  );
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "dispatchTouchEvent - actionUp");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "onTouchEvent - actionDown");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "onTouchEvent - actionMove"  );
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "onTouchEvent - actionUp");
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d(TAG, "onInterceptTouchEvent - actionDown");
               break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d(TAG, "onInterceptTouchEvent - actionMove");
                if (event.getRawY() > 1500) {
                    return true;
                } else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                LogUtil.d(TAG, "onInterceptTouchEvent - actionUp");
                break;

        }
        return super.onInterceptTouchEvent(event);
    }
}
