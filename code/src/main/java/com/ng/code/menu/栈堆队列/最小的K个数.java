package com.ng.code.menu.栈堆队列;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=295&tqId=23263&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
 * 数据范围：0\le k,n \le 100000≤k,n≤10000，数组中每个数的大小0 \le val \le 10000≤val≤1000
 * 要求：空间复杂度 O(n)O(n) ，时间复杂度 O(nlogn)O(nlogn)
 * <p>
 * 示例1
 * 输入：
 * [4,5,1,6,2,7,3,8],4
 * 返回值：
 * [1,2,3,4]
 * 说明：
 * 返回最小的4个数即可，返回[1,3,2,4]也可以
 * 示例2
 * 输入：
 * [1],0
 * 返回值：
 * []
 */
@Solution(easy = 0, hard = 0)
public class 最小的K个数 {

    public static void main(String[] args) {
        int[] data1 = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
        LogUtil.pring(EasySolution.GetLeastNumbers_Solution(data1, 4).toString());
    }

    /**
     * 快排？
     */
    private static class EasySolution {

        public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
            ArrayList<Integer> result = new ArrayList<>();
            qsort(input, 0, input.length - 1);
            for (int i = 0; i < k; i++) {
                result.add(input[i]);
            }
            return result;
        }

        //4, 5, 1, 6, 2, 7, 3, 8
        public static int[] qsort(int arr[], int start, int end) {
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

                LogUtil.pring(arr);
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
