package com.ng.code.menu.双指针;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/?envType
 * =study-plan-v2&envId=top-interview-150
 * 原题描述:
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例:
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前七个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅱ_删除有序数组中的重复项II {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    /**
     * 双指针
     */
    private static class EasySolution {
        public int removeDuplicates(int[] nums) {
            int n = nums.length;
            if (n <= 2) {
                return n;
            }
            int left = 2, right = 2;
            while (right < n) {
                if (nums[left - 2] != nums[right]) {
                    //必须找到与[left -2]不想等的right，才能对当前left进行赋值
                    nums[left] = nums[right];
                    left++;
                }
                right++;
            }
            return left;
        }
    }

    /**
     * 排序
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     */
    private static class HardSolution {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for (int num : nums) {
                if (i < 2 || num > nums[i - 2]) {
                    nums[i++] = num;
                }
            }
            return i;
        }
    }

}
