package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 平均时间复杂度：O(Nlog2N)
 * 最佳时间复杂度：
 * 最差时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_希尔排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.print(array);
    }

    /**
     * 设定步长 = length /2
     * 从步长~步长/2~步长/2/2...开始遍历
     * 每一次做当前步长到length的遍历，以当前步长为距离做插入排序
     */
    private static void sort(int[] array) {
        int gap = array.length;
        //1.设置步长
        while (gap > 0) {

            //2.插入排序(其中步长变成了gap)
            int preIndex;
            int cur;
            int index = gap;
            while (index < array.length) {
                cur = array[index];
                preIndex = index - gap;
                while (preIndex >= 0 && array[preIndex] > cur) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = cur;
                index += gap;
            }

            gap /= 2;
        }
    }
}
