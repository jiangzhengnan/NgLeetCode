package com.ng.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.airbnb.lottie.L;
import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;

public class PracticeClass {

    private void test() {
        LogUtil.print(coinChange(new int[]{1, 2, 5}, 11)); // 3

    }

    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        //某一位可以换到零钱的最小次数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}
