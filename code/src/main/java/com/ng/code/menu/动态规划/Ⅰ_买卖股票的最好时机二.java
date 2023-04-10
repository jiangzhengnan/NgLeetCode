package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * 原题描述:
 * 与一相比，多了多次
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你可以多次买卖该只股票，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 *
 * 示例:
 * 输入：
 * [8,9,2,5,4,7,1]
 * 返回值：
 * 7
 * 复制
 * 说明：
 * 在第1天(股票价格=8)买入，第2天(股票价格=9)卖出，获利9-8=1
 * 在第3天(股票价格=2)买入，第4天(股票价格=5)卖出，获利5-2=3
 * 在第5天(股票价格=4)买入，第6天(股票价格=7)卖出，获利7-4=3
 * 总获利1+3+3=7，返回7
 */
@Solution(easy = 1, hard = 0)
public class Ⅰ_买卖股票的最好时机二 {

    public static void main(String[] args) {
        int[] data = new int[]{8, 9, 2, 5, 4, 7, 1};
        LogUtil.print(EasySolution.maxProfit(data));
    }

    //直接求每一天比前一天赚了多少，累加
    private static class EasySolution {

        public static int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int len = prices.length;
            int result = 0;
            //假设当天卖出
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i-1]) {
                    result += prices[i] - prices[i-1];
                }
            }
            return result;
        }
    }

    private static class HardSolution {

    }

}
