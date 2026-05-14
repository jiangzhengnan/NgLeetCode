package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 */
@Solution(easy = 0, hard = 0)
public class Ⅰ_冒泡排序 {

    public static void main(String[] args) {
        int[] data = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(data);
        LogUtil.print(data);
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;  // 优化：提前退出
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {  // 相邻比较才是"冒泡"
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;  // 本轮无交换，已有序
        }
    }


}
