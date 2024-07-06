package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link: https://leetcode.cn/problems/interleaving-string/description/?envType=study-plan-v2
 * &envId=top-interview-150
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空
 * 子字符串
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_交错字符串 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        /*二维化
        https://leetcode.cn/problems/interleaving-string/solutions/335561/lei-si-lu-jing-wen-ti
        -zhao-zhun-zhuang-tai-fang-ch/?envType=study-plan-v2&envId=top-interview-150
        */
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            if (s3.length() != m + n) {
                return false;
            }
            // 动态规划，dp[i,j]表示s1前i字符能与s2前j字符组成s3前i+j个字符；
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
                dp[i][0] = true; // 不相符直接终止
            }
            for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
                dp[0][j] = true; // 不相符直接终止
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {

                    //到达（i，j）可能由（i-1,j）点向下一步，选择 s1[i-1] 到达；
                    //也可能由 （i,j-1） 点向右一步，选择 s2[j-1] 到达；
                    dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
                }
            }
            return dp[m][n];
        }

    }

    private static class HardSolution {

    }

}
