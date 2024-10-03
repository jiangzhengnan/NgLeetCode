package com.ng.code.menu.二分法;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/?envType
 * =study-plan-v2&envId=top-interview-150
 * 原题描述:
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * 示例:
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 3 次得到输入数组。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_寻找旋转排序数组中的最小值 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        public int findMin(int[] nums) {
            //只在有序的部分搜索
            int left = 0;
            int right = nums.length - 1;
            int res = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[left] <= nums[mid]) {
                    res = Math.min(res, nums[left]);
                    left = mid + 1;
                } else if (nums[mid] <= nums[right]) {
                    res = Math.min(res, nums[mid]);
                    right = mid - 1;
                }

            }
            return res;
        }

    }

    private static class HardSolution {

    }

}
