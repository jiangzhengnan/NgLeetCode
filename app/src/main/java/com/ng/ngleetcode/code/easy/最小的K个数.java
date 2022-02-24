
package com.ng.ngleetcode.code.easy;

import com.ng.ngleetcode.base.LogUtil;

import java.util.ArrayList;

/**
 * 日期:2022年2月19日 下午8:52:50
 * 原题链接:https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=117&tqId=37765&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * 完成次数:
 */
public class 最小的K个数 {

    public static void main(String[] args) {
        LogUtil.pring(GetLeastNumbers_Solution(new int[] { 4, 5, 1, 6, 2, 7, 3, 8 }, 4).toString());
    }

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>(k);
        //根据题意要求，如果K>数组的长度，返回一个空的数组
        if (k > input.length || k == 0)
            return res;
        quickSort(input, res, k, 0, input.length - 1);
        return res;
    }

    private static void quickSort(int[] input, ArrayList<Integer> res, int k, int left, int right) {
        //快排的实现方式有多种，我们选择了其中的一种
        int start = left;
        int end = right;
        while (left < right) {
            while (left < right && input[right] >= input[start]) {
                right--;
            }
            while (left < right && input[left] <= input[start]) {
                left++;
            }
            swap(input, left, right);
        }
        swap(input, left, start);
        //注意这里，start是数组中元素的下标。在start之前的元素都是比start指向的元素小，
        //后面的都是比他大。如果k==start，正好start之前的k个元素是我们要找的，也就是
        //数组中最小的k个，如果k>start，说明前k个元素不够，我们还要往后再找找。如果
        //k<start，说明前k个足够了，我们只需要在start之前找k个即可。
        if (left > k) {
            quickSort(input, res, k, start, left - 1);
        } else if (left < k) {
            quickSort(input, res, k, left + 1, end);
        } else {
            //取前面的k个即可
            for (int m = 0; m < k; ++m) {
                res.add(input[m]);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}