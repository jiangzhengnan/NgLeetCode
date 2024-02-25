package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅰ_冒泡排序 {

    public static void main(String[] args) {
        int[] data = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(data);
        LogUtil.print(data);
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j + 1 < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}
