package com.ng.code.menu.动态规划;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * https://leetcode.cn/problems/coin-change/?favorite=2ckc81c
 * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 * 如果无解，请返回-1.
 * 数据范围：数组大小满足 0 \le n \le 100000≤n≤10000 ， 数组中每个数字都满足 0 < val \le 100000<val≤10000，0 \le aim \le 50000≤aim≤5000
 * 要求：时间复杂度 O(n \times aim)O(n×aim) ，空间复杂度 O(aim)O(aim)。
 * <p>
 * 示例:
 * 示例1
 * 输入：
 * [5,2,3],20
 * 返回值：
 * 4
 * <p>
 * 示例2
 * 输入：
 * [5,2,3],0
 * 返回值：
 * 0
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_兑换零钱一 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.minMoney(new int[]{3,5,7}, 11));

    }

    private static class EasySolution {

        /**
         * 最少货币数
         *
         * @param arr int整型一维数组 the array
         * @param aim int整型 the target
         * @return int整型
         */
        public static int minMoney(int[] arr, int aim) {
            // write code here
            //如何使用最少arr元素 构成 aim值
            //dp[i] 代表给定钱数为i的时候最少货币数 就是凑成 i 元钱，需要dp[i] 张arr中面值纸币
            //没办法兑换 arr[i]  dp[i] = dp[i]
            //可以dp[i] = dp[i - arr[i]] + 1
            //dp[i] = min(dp[i], dp[i-a[j]])
            if (arr == null || arr.length == 0) {
                return -1;
            }
            int[] dp = new int[aim + 1];
            for (int i = 0; i <= aim; i++) {
                dp[i] = aim + 1;
            }
            LogUtil.pring(dp);

            dp[0] = 0;
            for (int i = 1; i <= aim; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] <= i) {
                        //给了一张 3 元钱，小于 需要找零的4 元钱，那 就等于 1 + 需要找零剩下的钱dp[i -arr[j]] 4 - 3
                        int now = dp[i];
                        int zhao = dp[i - arr[j]] + 1;
                        dp[i] = Math.min(now, zhao);
                    }
                }
            }
            LogUtil.pring(dp);
            return (dp[aim] > aim) ? -1 : dp[aim];
        }


    }

    private static class HardSolution {

    }

}
