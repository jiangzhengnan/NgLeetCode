package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 平均时间复杂度：O(n²)
 * 空间复杂度：O(1)
 *
 * 插入排序的思想是将待排序的序列分为已排序区间和未排序区间，每次从未排序区间中选取一个元素，将该元素插入到已排序区间中合适的位置，使得插入后仍然有序。
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_插入排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.print(array);
    }

    /**
     * 第一个元素默认已排序
     * 遍历0到length-1的下标元素,i
     * 每个元素每一次从 i + 1 到0位置遍历，遇到比自己大的就后挪，
     * 比自己小的就留在当前位置
     */
    private static void sort(int[] array) {
        // 第一个元素默认已排序
        int index = 1;
        while (index <= array.length - 1) {
            int preIndex = index - 1;
            int now = array[preIndex + 1];
            while (preIndex >= 0 && array[preIndex] > array[preIndex + 1]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = now;
            index++;
        }

    }


}
