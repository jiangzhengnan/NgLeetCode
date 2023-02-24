package com.ng.code.menu.排序;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/04/16
 * @description :
 * 平均时间复杂度：O(Nlog2N)
 * 最佳时间复杂度：
 * 最差时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_希尔排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.pring(array);
    }

    /**
     * 设定步长 = length /2
     * 从步长~步长/2~步长/2/2...开始遍历
     * 每一次做当前步长到length的遍历，以当前步长为距离做插入排序
     */
    private static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        int temp;
        int gap = length / 2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }

    }
}
