package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * link:
 * 原题描述:
 * 与一，二的区别在于只有两笔操作,买入和卖出，并且再次购买前必须卖出之前的股票
 *
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 *
 * 示例:
 * 示例1
 * 输入：
 * [8,9,3,5,1,3]
 * 返回值：
 * 4
 * 说明：
 * 第三天(股票价格=3)买进，第四天(股票价格=5)卖出，收益为2
 * 第五天(股票价格=1)买进，第六天(股票价格=3)卖出，收益为2
 * 总收益为4。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_买卖股票的最好时机三 {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * 状态定义：第一维表示交易天数，第二维表示交易次数，第三维表示是否持有股票状态（分未持有股票和持有股票两种）。
     * 状态转移：
     * 当某一天第k次交易，并处于未持有股票状态时，其最大值：
     * 要么是之前某一天第k次交易未持有股票状态的最大值，
     * 要么是之前某一天持有股票状态卖出当天的股票，
     * 即 dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
     * <p>
     * 当某一天第k次交易，并处于持有股票状态时，其最大值：
     * 要么是之前某一天第k次交易持有股票状态的最大值，
     * 要么是之前某一天第k-1次交易未持有股票状态买入当天的股票（不能同时持有两只股票），
     * 即 dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     */
    private static class EasySolution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            //最大交易次数
            int maxK = 2;

            //初始化dp数组，天数，交易次数，持股状态
            int[][][] dp = new int[n][maxK + 1][2];
            for (int i = 0; i < n; i++) {
                for (int k = maxK; k >= 1; k--) {
                    if (i == 0) {
                        //给每个状态赋初值
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[0];
                    } else {
                        //状态转移
                        dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                        dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                    }

                }

            }
            return dp[n - 1][maxK][0];
        }
    }


}
