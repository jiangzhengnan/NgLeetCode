package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan
 * -v2&envId=top-100-liked
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
@Solution(easy = 1, hard = 1, particle = 1)
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
