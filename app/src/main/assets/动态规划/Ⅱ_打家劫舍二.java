package com.ng.code.menu.动态规划;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.Arrays;

/**
 * link:https://www.nowcoder.com/practice/a5c127769dd74a63ada7bff37d9c5815?tpId=295&tqId=2285837&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 你是一个经验丰富的小偷，准备偷沿湖的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
 * 即，如果偷了第一家，就不能再偷第二家，如果偷了第二家，那么就不能偷第一家和第三家。
 * 沿湖的房间组成一个闭合的圆形，即第一个房间和最后一个房间视为相邻。
 * 给定一个长度为n的整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
 *
 * 示例:
 * 示例1
 * 输入：
 * [1,2,3,4]
 * 返回值：
 * 6
 *
 * 说明：
 * 最优方案是偷第 2 4 个房间
 * 示例2
 * 输入：
 * [1,3,6]
 * 返回值：
 * 6
 *
 * 说明：
 * 由于 1 和 3 是相邻的，因此最优方案是偷第 3 个房间
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_打家劫舍二 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.getRob(new int[]{1, 2, 3, 4}));
    }

    /**
     * 动态规划
     * 状态定义：dp[i]表示到第i个房间为止，能偷到的最多的现金。
     * 状态初始化：到第0个房间时，最多偷第0个房间的现金。到第1个房间时，最多偷第0个房间或第1个房间的现金，两者中取较大者。
     * 状态转移：要么是前前家+当前，要么是前一家，取较大者。
     * 即dp[i]=Math.max(dp[i−1],dp[i−2]+nums[i])
     * 首先在nums的0到n-2的房子中找，然后在1到n-1的房子中找，取两者中的较大者。
     */
    private static class EasySolution {

        public static int rob(int[] nums) {
            int n = nums.length;
            //因为是圆形，所以要首尾隔开
            //在0到n-2范围内找
            int rob1 = getRob(Arrays.copyOfRange(nums, 0, n - 1));
            //在1到n-1范围内找
            int rob2 = getRob(Arrays.copyOfRange(nums, 1, n));

            return Math.max(rob1, rob2);
        }

        private static int getRob(int[] nums) {
            int n = nums.length;
            //边界情况处理
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            if (n == 2) return Math.max(nums[0], nums[1]);
            //定义dp数组
            int[] dp = new int[n];
            //初始化
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                //状态转移
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[n - 1];
        }

    }

    private static class HardSolution {

    }

}
