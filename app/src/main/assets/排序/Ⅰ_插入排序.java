package com.ng.code.menu.排序;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : 
 * @creation : 2022/07/04
 * @description :
 * 平均时间复杂度：O(n²)
 * 空间复杂度：O(1)
 */
@Solution(easy = 0, hard = 0, partice = 0)
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
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int cur = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && cur < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex -- ;
            }
            array[preIndex + 1] = cur;
        }

    }


}
