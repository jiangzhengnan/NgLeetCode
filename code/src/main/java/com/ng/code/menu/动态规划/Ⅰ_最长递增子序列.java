package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * https://leetcode.cn/problems/longest-increasing-subsequence/description/
 *
 * 原题描述:
 * 描述
 * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
 * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
 * 我们定义一个序列是 严格上升 的，当且仅当该序列不存在两个下标 i 和 j 满足 i<j 且 arr_i < arr_j
 * 数据范围： 0\leq n \leq 10000≤n≤1000
 * 要求：时间复杂度 O(n^2)  空间复杂度 O(n)
 * <p>
 * 示例:
 * 输入：
 * [6,3,1,5,2,3,7]
 * 返回值：
 * 4
 * 说明：
 * 该数组最长上升子序列为 [1,2,3,7] ，长度为4
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅰ_最长递增子序列 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.lengthOfLIS(new int[]{6, 3, 1, 5, 2, 3, 7}));
    }

    /**
     * 动态规划
     * dp[i]表示以下标i结尾的最长上升子序列长度
     */
    private static class EasySolution {

        public static int lengthOfLIS(int[] nums) {
            //动态规划
            int result = 0;
            //dp[i]表示以下标i结尾的最长上升子序列长度
            int[] dp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                    }
                }
                result = Math.max(result, dp[i]);
            }


            return result;
        }

    }

    private static class HardSolution {

    }

}
