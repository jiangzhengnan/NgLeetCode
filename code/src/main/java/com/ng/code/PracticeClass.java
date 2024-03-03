package com.ng.code;


import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        int[] data = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(data);
        LogUtil.print(data);
    }

    private void sort(int[] data) {


    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}




