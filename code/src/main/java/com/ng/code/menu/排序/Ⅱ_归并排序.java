package com.ng.code.menu.排序;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/04/16
 * @description :
 * 算法复杂度：O(nlogn)
 * 算法空间复杂度：O(n)
 * 算法稳定性：稳定
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_归并排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.pring(array);
    }

    protected static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //归并排序
        mergeSort(0, nums.length - 1, nums);
    }

    static int[] temp = new int[9];

    private static void mergeSort(int star, int end, int[] nums) {
        //递归终止条件
        if (star >= end) {
            return;
        }
        int mid = star + (end - star) / 2;
        //左边进行归并排序
        mergeSort(star, mid, nums);
        //右边进行归并排序
        mergeSort(mid + 1, end, nums);
        //合并左右
        merge(star, end, mid, nums);
    }

    private static void merge(int star, int end, int mid, int[] nums) {
        int index = 0;
        int left = star;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (nums[left] > nums[right]) {
                temp[index++] = nums[right++];
            } else {
                temp[index++] = nums[left++];
            }
        }
        while (left <= mid) {
            temp[index++] = nums[left++];
        }
        while (right <= end) {
            temp[index++] = nums[right++];
        }
        for (int k = 0; k < index; k++) {
            nums[star + k] = temp[k];
        }
    }
}
