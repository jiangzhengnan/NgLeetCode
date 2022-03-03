package com.ng.ngleetcode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class NgTextView extends AppCompatTextView {
    int mode = 0;
    float mOldDist = 0f;
    float mTextSize = 0f;

    public NgTextView(@NonNull Context context) {
        super(context);
    }

    public NgTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NgTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTextSize == 0f) {
            mTextSize = getTextSize();
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mOldDist = 0f;
                mode = 1;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode -= 1;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mOldDist = spacing(event);
                mode += 1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode >= 2) {
                    float newDist = spacing(event);
                    if (Math.abs(newDist - mOldDist) > 50) {
                        zoom(newDist / mOldDist);
                        mOldDist = newDist;
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    private void zoom(float f) {
        mTextSize *= f;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getX(0) - event.getX(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
