package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * link:
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/?envType=study
 * -plan-v2&envId=top-interview-150
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_买卖股票的最好时机四 {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * https://labuladong.online/algo/dynamic-programming/stock-problem-summary/#%E4%BA%8C%E3%80%81%E7%8A%B6%E6%80%81%E8%BD%AC%E7%A7%BB%E6%A1%86%E6%9E%B6
     */
    private static class EasySolution {
        public int maxProfit(int maxK, int[] prices) {

            int n = prices.length;
            // //最大交易次数
            // int maxK = 2;

            //初始化dp数组
            /**
             * [天数][最大可交易次数上限][0 和 1 代表是否持有股票]
             *
             * 每天都有三种「选择」：买入、卖出、无操作，我们用 buy, sell, rest 表示这三种选择。
             */
            int[][][] dp = new int[n][maxK + 1][2];


            for (int i = 0; i < n; i++) {
                for (int k = maxK; k >= 1; k--) {
                    if (i == 0) {
                        //给每个状态赋初值
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[0];
                    } else {
                        //状态转移

                        dp[i][k][0] = Math.max(
                            dp[i - 1][k][0],
                            //昨天就没有持有，且截至昨天最大交易次数限制为 k；然后我今天选择 不操作，所以我今天还是没有持有，最大交易次数限制依然为 k。
                            dp[i - 1][k][1] + prices[i]
                            //昨天持有股票，且截至昨天最大交易次数限制为 k；但是今天我 sell 了，所以我今天没有持有股票了，最大交易次数限制依然为 k。

                        );

                        dp[i][k][1] = Math.max(
                            dp[i - 1][k][1],
                            //昨天就持有着股票，且截至昨天最大交易次数限制为 k；然后今天选择 不操作，所以我今天还持有着股票，最大交易次数限制依然为 k。

                            dp[i - 1][k - 1][0] - prices[i]
                            //昨天本没有持有，且截至昨天最大交易次数限制为 k - 1；但今天我选择 buy，所以今天我就持有股票了，最大交易次数限制为 k。

                        );
                    }

                }

            }
            return dp[n - 1][maxK][0];

        }
    }


}
