package com.ng.ngleetcode.stat;

import com.ng.base.utils.LogUtil;

public class AdStatHelper {


    public static void start() {
        LogUtil.print("start :" + getDescInfo());
    }

    public static void record() {
        LogUtil.print("record :" + getDescInfo());
    }

    public static void end() {
        LogUtil.print("end :" + getDescInfo());
    }

    public static String getDescInfo() {
        return getClassName() + "-" + getMethodName() + "-" + getLineNumber();
    }

    private static int originStackIndex = 4;

    public static String getFileName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getFileName();
    }

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getClassName();
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getMethodName();
    }

    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getLineNumber();
    }
}
