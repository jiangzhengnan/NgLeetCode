package com.ng.code.menu.other;


import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 类似斐波那契
 * 递归解法比较简单这里不提了
 */
@Solution(easy = 1, hard = 0)
public class 跳台阶 {

    public static void main(String[] args) {
        LogUtil.pring(jumpFloor(7));
    }

    public static int jumpFloor(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
