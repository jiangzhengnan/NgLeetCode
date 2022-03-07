// Copyright 2016 UCWeb Co., Ltd.

package com.ng.ngleetcode.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLES10;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.microedition.khronos.opengles.GL10;

public class UIUtil {
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYER_TYPE_HARDWARE = 2;

    public static int dp2px(Context context, float dpValue) {
        DisplayMetrics metrics;
        if (context != null) {
            metrics = context.getResources().getDisplayMetrics();
        } else {
            metrics = Resources.getSystem().getDisplayMetrics();
        }
        return (int) (dpValue * metrics.density + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        DisplayMetrics metrics;
        if (context != null) {
            metrics = context.getResources().getDisplayMetrics();
        } else {
            metrics = Resources.getSystem().getDisplayMetrics();
        }
        return (int) ((pxValue - 0.5f) / metrics.density);
    }

    public static Drawable transformToGrayDrawable(Drawable aDrawable) {
        if (null == aDrawable) {
            return null;
        }

        ColorMatrix targetCm = new ColorMatrix();
        targetCm.setSaturation(0);
        ColorMatrix lightCm = new ColorMatrix();
        int brightness = -0x66;
        lightCm.set(new float[]{
                1, 0, 0, 0, brightness,
                0, 1, 0, 0, brightness,
                0, 0, 1, 0, brightness,
                0, 0, 0, 1, 0
                });

        targetCm.postConcat(lightCm);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(targetCm);
        aDrawable.setColorFilter(f);
        return aDrawable;
    }

    private static int getGlMaxTextureSize() {
        int[] maxTextureSize = new int[1];
        GLES10.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);
        return maxTextureSize[0];
    }

    private static int getIntFileValueFromClass(Class c, String filedName) {
        Field fild = null;
        int fildValue = 0;
        try {
            fild = c.getDeclaredField(filedName);
            fild.setAccessible(true);
            fildValue = fild.getInt(c);

        } catch (NoSuchFieldException ex) {
        } catch (Exception ex) {
        }

        return fildValue;
    }

    private static void setLayerType(View v, int type) {
        try {
            Integer realType = -1;
            switch (type) {
            case LAYER_TYPE_NONE:
                realType = getIntFileValueFromClass(
                        View.class, "LAYER_TYPE_NONE");
                break;
            case LAYER_TYPE_SOFTWARE:
                realType = getIntFileValueFromClass(
                        View.class, "LAYER_TYPE_SOFTWARE");
                break;
            case LAYER_TYPE_HARDWARE:
                realType = getIntFileValueFromClass(
                        View.class, "LAYER_TYPE_HARDWARE");
                break;
            default:
                throw new RuntimeException("unsupported layer type");
            }

            if (-1 == realType) {
                return;
            }

            Class<View> cls = View.class;
            @SuppressWarnings("rawtypes")
            Class paramtypes[] = new Class[2];
            paramtypes[0] = Integer.TYPE;
            paramtypes[1] = Paint.class;
            Method method = cls.getMethod("setLayerType", paramtypes);
            Object arglist[] = new Object[2];
            arglist[0] = realType;
            arglist[1] = null;
            method.invoke(v, arglist);
        } catch (Exception ex) {
        }
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
