package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * 原题描述:
 * 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
 * 2.如果不能获取到任何利润，请返回0
 * 3.假设买入卖出均无手续费
 * 空间复杂度 O(1) ，时间复杂度 O(n)
 *
 * 示例:
 * 示例1
 * 输入：
 * [8,9,2,5,4,7,1]
 * 返回值：
 * 5
 *
 * 说明：
 * 在第3天(股票价格 = 2)的时候买入，在第6天(股票价格 = 7)的时候卖出，最大利润 = 7-2 = 5 ，不能选择在第2天买入，第3天卖出，这样就亏损7了；同时，你也不能在买入前卖出股票。
 * 示例2
 * 输入：
 * [2,4,1]
 * 返回值：
 * 2
 *
 * 示例3
 * 输入：
 * [3,2,1]
 * 返回值：
 * 0
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅰ_买卖股票的最好时机一 {

    public static void main(String[] args) {
        int[] data = new int[]{8, 9, 2, 5, 4, 7, 1};
        LogUtil.print(EasySolution.maxProfit(data));
    }


    /**
     * 暴力循环
     * 时间复杂度：O(N^2), N代表循环数组长度，具体为循环运行：
     * 空间复杂度：O(1),常数空间
     */
    private static class EasySolution {

        //解法一：暴力大循环
        public static int maxProfit(int[] prices) {
            int ans = 0;
            int len = prices.length;
            //爱的魔力for循环
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    int tmp_ans = prices[j] - prices[i];
                    //更新答案
                    if (tmp_ans > ans) {
                        ans = tmp_ans;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 我们假设自己购买股票，为了达到利润最大化，必然会挑一个历史最低点进行买入,
     * 声明最低价格minPrices
     * 那么在第i天卖出的股票的利润就是Prices[i]-minPrices
     * 如此找到数组中历史最低还不简单？
     * 比如图中第二天
     * 一趟遍历记录最低点即可
     * 时间复杂度：O(N),主要为一层循环的开销
     * 空间复杂度:O(1) ，常数空间
     */
    private static class HardSolution {

        //暴力解法优化
        public int maxProfit (int[] prices) {
            // write code here
            int len = prices.length;
            int minPrices = Integer.MAX_VALUE;
            int ans = 0;
            for(int i=0;i<len;i++){
                //寻找最低点
                if(prices[i]<minPrices){
                    minPrices = prices[i];
                }else if(prices[i]-minPrices>ans){
                    //更新答案（最大利润）
                    ans = prices[i]-minPrices;
                }
            }
            return ans;
        }
    }

}
