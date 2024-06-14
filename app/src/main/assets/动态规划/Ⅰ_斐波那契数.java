package com.ng.code.menu.动态规划;


import com.ng.base.utils.LogUtil;
import com.ng.code.menu.Ⅰ_Ⅱ_Ⅲ_TemplateTestClass;
import com.ng.code.util.Solution;

/**
 * link:https://leetcode.cn/problems/fibonacci-number/description/
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅰ_斐波那契数 {

    public static void main(String[] args) {

    }

    /**
     *
     */
    private static class EasySolution {
        //迭代 时间复杂度On
        public static int fib(int n) {
            if (n <= 1) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;

            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        //递归 时间复杂度是 o2n，很低效。
//    public static int fib(int n) {
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        }
//        return fib(n - 1) + fib(n - 2);
//    }


//        /**
//         * 递归缓存版本，更加高效，时间复杂度On
//         */
//        int fib(int N) {
//            // 备忘录全初始化为 0
//            int[] memo = new int[N + 1];
//            // 进行带备忘录的递归
//            return dp(memo, N);
//        }
//
//        // 带着备忘录进行递归
//        int dp(int[] memo, int n) {
//            // base case
//            if (n == 0 || n == 1) {
//                return n;
//            }
//            // 已经计算过，不用再计算了
//            if (memo[n] != 0) {
//                return memo[n];
//            }
//            memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
//            return memo[n];
//        }
    }

    private static class HardSolution {


        /**
         * 根据斐波那契数列的状态转移方程，当前状态 n 只和之前的 n-1, n-2 两个状态有关，其实并不需要那么长的一个 DP table
         * 来存储所有的状态，只要想办法存储之前的两个状态就行了。
         * 所以，可以进一步优化，把空间复杂度降为 O(1)。这也就是我们最常见的计算斐波那契数的算法：
         */
        int fib(int n) {
            if (n == 0 || n == 1) {
                // base case
                return n;
            }
            // 分别代表 dp[i - 1] 和 dp[i - 2]
            int dp_i_1 = 1, dp_i_2 = 0;
            for (int i = 2; i <= n; i++) {
                // dp[i] = dp[i - 1] + dp[i - 2];
                int dp_i = dp_i_1 + dp_i_2;
                // 滚动更新
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i_1;
        }


    }

}
