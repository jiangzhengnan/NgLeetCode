package com.ng.ngleetcode.stat;

public class StatMain {

    /**
     * 多点统计。
     */
    public static void main(String[] args) {
        AdStatHelper.start();
        method1();
        method2();
        method3();
        method4();
        method5();
        AdStatHelper.end();

    }

    private static void method5() {
        AdStatHelper.record();
    }

    private static void method4() {
        AdStatHelper.record();
    }

    private static void method3() {
        AdStatHelper.record();
    }

    private static void method2() {
        AdStatHelper.record();
    }

    private static void method1() {
        AdStatHelper.record();
    }


}
