package com.ng.code.menu.动态规划;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.Arrays;

/**
 * 原题描述:
 * https://leetcode.cn/problems/coin-change/?favorite=2ckc81c
 * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
 * 如果无解，请返回-1.
 * 数据范围：数组大小满足 0 \le n \le 100000≤n≤10000 ， 数组中每个数字都满足 0 < val \le 100000<val≤10000，0 \le aim \le 50000≤aim≤5000
 * 要求：时间复杂度 O(n \times aim)O(n×aim) ，空间复杂度 O(aim)O(aim)。
 * 示例1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_兑换零钱一 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.coinChange(new int[]{2}, 3));

    }

    private static class EasySolution {

        /**
         * https://leetcode.cn/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
         * @param coins
         * @param amount
         * @return
         */
        public static int coinChange(int[] coins, int amount) {
            int max = amount +1 ;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }


    }

    private static class HardSolution {

    }

}
