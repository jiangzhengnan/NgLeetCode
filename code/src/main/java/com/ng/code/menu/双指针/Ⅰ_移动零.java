package com.ng.code.menu.双指针;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=top-100-liked
 * 原题描述:
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅰ_移动零 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        int[] data = new int[]{0, 1, 0, 3, 12};
        easySolution.moveZeroes(data);
        LogUtil.print(data);
    }

    private static class EasySolution {

        /**
         * 思想：把非0数都移动到左指针左侧
         *
         * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
         * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
         *
         * 注意到以下性质：
         * 左指针左边均为非零数；
         * 右指针左边直到左指针处均为零。
         * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
         */
        public void moveZeroes(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;

            }
        }

        public void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

    }


    /**
     * 同上
     */
    private static class HardSolution {

    }

}
