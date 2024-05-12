package com.ng.code.menu.哈希;

import java.util.HashMap;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2
 * &envId=top-100-liked
 * 原题描述:
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_和为K的子数组 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        /**
         * 枚举
         * 考虑以 iii 结尾和为 kkk 的连续子数组个数，我们需要统计符合条件的下标 jjj 的个数，其中 0≤j≤i0\leq j\leq i0≤j≤i 且 [j..i][j.
         * .i][j..i] 这个子数组的和恰好为 kkk 。
         * 我们可以枚举 [0..i][0..i][0..i] 里所有的下标 jjj 来判断是否符合条件，可能有读者会认为假定我们确定了子数组的开头和结尾，还需要 O(n)O(n)O
         * (n) 的时间复杂度遍历子数组来求和，那样复杂度就将达到 O(n3)O(n^3)O(n3
         * ) 从而无法通过所有测试用例。但是如果我们知道 [j,i][j,i][j,i] 子数组的和，就能 O(1)O(1)O(1) 推出 [j−1,i][j-1,i][j−1,
         * i] 的和，因此这部分的遍历求和是不需要的，我们在枚举下标 jjj 的时候已经能 O(1)O(1)O(1) 求出 [j,i][j,i][j,i] 的子数组之和。
         */
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            for (int start = 0; start < nums.length; ++start) {
                int sum = 0;
                for (int end = start; end >= 0; --end) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }

    }

    private static class HardSolution {

        /**
         * 前缀和 + 哈希表优化
         */
        public int subarraySum(int[] nums, int k) {
            int count = 0, pre = 0;
            HashMap<Integer, Integer> mp = new HashMap<>();
            mp.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                if (mp.containsKey(pre - k)) {
                    count += mp.get(pre - k);
                }
                mp.put(pre, mp.getOrDefault(pre, 0) + 1);
            }
            return count;
        }
    }

}
