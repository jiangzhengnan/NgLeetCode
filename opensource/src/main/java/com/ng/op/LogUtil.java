package com.ng.op;

import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author : 
 * @creation : 2023/02/15
 * @description :
 */
public class LogUtil {
    public static void d(@Nullable String msg) {
        if (msg == null) {
            return;
        }
        Log.d("nangua", msg);
    }

    public static void print(@Nullable String msg) {
        if (msg == null) {
            return;
        }
        System.out.println(msg);
    }

    public static void print(@Nullable Integer msg) {
        if (msg == null) {
            return;
        }
        System.out.println(msg);
    }
}
