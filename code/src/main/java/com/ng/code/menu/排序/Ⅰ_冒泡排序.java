package com.ng.code.menu.排序;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/07/04
 * @description :
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_冒泡排序 {

    public static void main(String[] args) {
        int[] data = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(data);
        LogUtil.pring(data);
    }

    //maopao
    public static void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int maxIndex = 0;
            for (int j = 0; j < (array.length - i); j++) {
                if (array[j] >= array[maxIndex]) {
                    maxIndex = j;
                }
            }
            LogUtil.pring(i + "交换:" + array[array.length - i - 1] +" " + array[maxIndex]);

            int temp = array[array.length - i - 1];
            array[array.length - i - 1] = array[maxIndex];
            array[maxIndex] = temp;

            LogUtil.pring(array);
        }
    }

}
