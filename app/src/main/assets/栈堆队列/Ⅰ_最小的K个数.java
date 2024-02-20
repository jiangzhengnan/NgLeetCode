package com.ng.code.menu.栈堆队列;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/smallest-k-lcci/description/
 * 原题描述:
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
@Solution(easy = 0, hard = 0, partice = 1)
public class Ⅰ_最小的K个数 {

    public static void main(String[] args) {
        int[] data1 = new int[]{1,3,5,7,2,4,6,8};
        LogUtil.print(EasySolution.smallestK(data1, 4).toString());
    }

    /**
     * 快排？
     */
    private static class EasySolution {

        public static int[] smallestK(int[] arr, int k) {
            int[] result = new int[k];
            qsort(arr, 0, arr.length - 1);
            System.arraycopy(arr, 0, result, 0, k);
            return result;
        }

        public static int[] qsort(int arr[], int start, int end) {
            if (start >= end) {
                return arr;
            }
            int pivot = arr[start];
            int i = start;
            int j = end;
            while (i < j) {
                while ((i < j) && (arr[j] > pivot)) {
                    j--;
                }
                while ((i < j) && (arr[i] < pivot)) {
                    i++;
                }

                if ((arr[i] == arr[j]) && (i < j)) {
                    i++;
                } else {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
            if (i - 1 > start) arr = qsort(arr, start, i - 1);
            if (j + 1 < end) arr = qsort(arr, j + 1, end);
            return (arr);
        }
    }

    //同上
    private static class HardSolution {

    }

}
