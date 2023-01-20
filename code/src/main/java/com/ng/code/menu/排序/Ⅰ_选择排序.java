package com.ng.code.menu.排序;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/07/04
 * @description :
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 */
@Solution(easy = 0, hard = 1, partice = 1)
public class Ⅰ_选择排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.pring(array);
    }

    /**
     * 每次找到最小的元素，然后和[第几次操作]下标交换位置
     */
    private static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }

    }


}
