package com.ng.train;

import androidx.annotation.Nullable;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/15
 * @description :
 */
public class LogUtil {

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
