package com.ng.code.menu.动态规划;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 给定一个整数数组 cost   ，其中 cost[i] 是从楼梯第i \i 个台阶向上爬需要支付的费用，下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * 示例:
 * 示例1
 * 输入：
 * [2,5,20]
 * 返回值：
 * 5
 *
 * 说明：
 * 你将从下标为1的台阶开始，支付5 ，向上爬两个台阶，到达楼梯顶部。总花费为5
 * 示例2
 * 输入：
 * [1,100,1,1,1,90,1,1,80,1]
 * 返回值：
 * 6
 *
 * 说明：
 * 你将从下标为 0 的台阶开始。
 * 1.支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * 2.支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * 3.支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * 4.支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * 5.支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * 6.支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_最小花费爬楼梯 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 100, 1, 1, 1, 90, 1, 1, 80, 1};
        LogUtil.print(EasySolution.minCostClimbingStairs(data));
    }

    /**
     * 走到当前台阶是从哪一步，dp[i-1]和dp[i-2]
     * 加上当前当前台阶的花费
     */
    private static class EasySolution {
        public static int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < n; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
            return Math.min(dp[n - 1], dp[n - 2]);
        }
    }

    private static class HardSolution {

    }

}
