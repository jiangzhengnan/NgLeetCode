package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 总利润为 4 。
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_买卖股票的最好时机二 {

    public static void main(String[] args) {
        int[] data = new int[]{8, 9, 2, 5, 4, 7, 1};
        LogUtil.print(EasySolution.maxProfit(data));
    }

    //直接求每一天比前一天赚了多少，累加
    private static class EasySolution {

        //因为交易次数不受限，如果可以把所有的上坡全部收集到，一定是利益最大化的
        public static int maxProfit(int[] prices) {
            int max = 0;
            int prePrice = Integer.MAX_VALUE;
            for (int nowPrice : prices) {
                max = Math.max(max, max + nowPrice - prePrice);
                prePrice = nowPrice;
            }
            return max;
        }
    }

    private static class HardSolution {

    }

}
