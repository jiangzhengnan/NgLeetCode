package com.ng.code.menu.二分法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link：
 * https://leetcode.cn/problems/binary-search/
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_二分查找一 {

    public static void main(String[] args) {
        LogUtil.print(HardSolution.search(new int[]{-1, 0, 3, 4, 6, 10, 13, 14}, 13));
    }

    /**
     * 递归
     */
    private static class EasySolution {
        public static int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            // write code here
            return search(nums, target, 0, nums.length);
        }

        public static int search(int[] nums, int target, int start, int end) {
            if (start > end) {
                return -1;
            }
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                mid--;
                return search(nums, target, start, mid);
            } else {
                mid++;
                return search(nums, target, mid, end);
            }
        }
    }

    /**
     * 迭代
     */
    private static class HardSolution {

        public static int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return nums[r] == target ? r : -1;

        }
    }


}
