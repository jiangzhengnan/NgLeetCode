package com.ng.code;


import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(data);
        LogUtil.print(data);
    }

    private void sort(int[] data) {
        //冒泡
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j + 1 < data.length - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
                LogUtil.print(data);

            }

        }

    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}




