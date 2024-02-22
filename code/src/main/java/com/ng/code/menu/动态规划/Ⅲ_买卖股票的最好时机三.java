package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * link:https://www.nowcoder.com/practice/4892d3ff304a4880b7a89ba01f48daf9?tpId=295&tqId=1073487&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
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
     * 状态转移：当某一天第k次交易，并处于未持有股票状态时，其最大值，要么是之前某一天第k次交易未持有股票状态的最大值，
     * 要么是之前某一天持有股票状态卖出当天的股票，即
     * dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
     * 当某一天第k次交易，并处于持有股票状态时，其最大值，要么是之前某一天第k次交易持有股票状态的最大值，
     * 要么是之前某一天第k-1次交易未持有股票状态买入当天的股票（不能同时持有两只股票），即
     * dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     */
    private static class EasySolution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            //最大交易次数
            int maxK = 2;

            //初始化dp数组
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
            return dp[n - 1][2][0];
        }
    }

    /**
     * 动态规划+细分状态
     * 由于最多可以进行两次交易，我们可以分出四种交易状态：
     *
     * 只进行过一次买操作，记为buy1。
     * 进行过一次买操作和一次卖操作，记为sell1。
     * 完成一笔交易后，又进行一次买操作，记为buy2。
     * 完成了两笔交易，记为sell2。
     * 因为要买入股票，buy1和buy2的初值都是图片说明 ，buy1和buy2的初值都是0。
     * 状态转移过程中，每一种状态的最大值，要么是对应前一种状态转化而来
     * （buy1卖出股票变为sell1状态，sell1买入股票变为buy2，buy2卖出股票变为sell2），要么保持当前状态。
     */
    private static class HardSolution {

        public int maxProfit(int[] prices) {
            int n = prices.length;

            //赋初值
            int buy1 = -prices[0], sell1 = 0;
            int buy2 = -prices[0], sell2 = 0;

            for (int i = 1; i < n; i++) {
                //状态转移
                buy1 = Math.max(buy1, -prices[i]);
                sell1 = Math.max(sell1, buy1 + prices[i]);
                buy2 = Math.max(buy2, sell1 - prices[i]);
                sell2 = Math.max(sell2, buy2 + prices[i]);
            }
            return sell2;
        }

    }

}
