package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://leetcode.cn/problems/longest-common-subsequence/description/
 *
 * 原题描述:
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 *
 * 数据范围：0 \le |str1|,|str2| \le 20000≤∣str1∣,∣str2∣≤2000
 * 要求：空间复杂度 O(n^2)  时间复杂度 O(n^2)
 *
 * 示例:
 * 示例1
 * 输入：
 * "1A2C3D4B56","B1D23A456A"
 * 返回值：
 * "123456"
 *
 * 示例2
 * 输入：
 * "abc","def"
 * 返回值：
 * "-1"
 *
 * 示例3
 * 输入：
 * "abc","abc"
 * 返回值：
 * "abc"
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_最长公共子序列 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.longestCommonSubsequence("1A2C3D4B56", "B1D23A456A"));
    }

    //解法:https://blog.csdn.net/hrn1216/article/details/51534607
    private static class EasySolution {

        public static int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                char c1 = text1.charAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    char c2 = text2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[m][n];
        }
    }

    private static class HardSolution {

    }

}
