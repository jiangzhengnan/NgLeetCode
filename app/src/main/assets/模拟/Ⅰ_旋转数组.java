package com.ng.code.menu.模拟;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * https://leetcode.cn/problems/rotate-array/description/
 * <p>
 * 原题描述:
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_旋转数组 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        EasySolution.rotate(data, 3);

        LogUtil.print(data);
    }

    //new一个数组，依次取值
    private static class EasySolution {

        public static void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; ++i) {
                newArr[(i + k) % n] = nums[i];
            }
            System.arraycopy(newArr, 0, nums, 0, n);
        }


    }

    /**
     * 原始数组	  1 2 3 4 5 6 7
     * 翻转所有元素	7 6 5 4 3 2 1
     * 翻转 0,k-1 区间的元素	5 6 7 4 3 2 1
     * 翻转 k,n-1 区间的元素	5 6 7 1 2 3 4
     */
    private static class HardSolution {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            revers(nums, 0, nums.length - 1);
            revers(nums, 0, k - 1);
            revers(nums, k, nums.length - 1);
        }

        private void revers(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
                start++;
                end--;
            }

        }


    }

}
