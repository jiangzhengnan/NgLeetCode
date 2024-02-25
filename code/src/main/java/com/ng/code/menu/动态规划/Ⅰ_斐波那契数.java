package com.ng.code.menu.动态规划;


import com.ng.base.utils.LogUtil;
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
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_斐波那契数 {

    public static void main(String[] args) {
        LogUtil.print(fib(7));
    }

    //迭代
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

    //递归
//    public static int fib(int n) {
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        }
//        return fib(n - 1) + fib(n - 2);
//    }
}
