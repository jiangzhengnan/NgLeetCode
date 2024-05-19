package com.ng.code.menu.双指针;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link: https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/?envType
 * =study-plan-v2&envId=top-interview-150
 * 原题描述:
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums
 * 中唯一元素的个数。
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * 示例:
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅰ_删除有序数组中的重复项 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

    }

    private static class HardSolution {

        /**
         * 首先注意数组是有序的，那么重复的元素一定会相邻。
         * 要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
         * 考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：
         * 比较 left 和 right 位置的元素是否相等。
         * 如果相等，right 后移 1 位
         * 如果不相等，将 right 位置的元素复制到 left+1 位置上，left 后移一位，right 后移 1 位
         * 重复上述过程，直到 right 等于数组长度。
         * 返回 left + 1，即为新数组长度。
         */
        public int removeDuplicates(int[] nums) {
            int left = 0;
            int right = 1;
            while (right < nums.length) {
                if (nums[left] != nums[right]) {
                    nums[left + 1] = nums[right];
                    left++;
                }
                right++;
            }
            return left + 1;
        }
    }

}
