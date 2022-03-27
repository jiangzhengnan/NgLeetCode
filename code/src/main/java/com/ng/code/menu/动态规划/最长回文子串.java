package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 * 进阶:  空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 *
 * 示例:
 * 示例1
 * 输入：
 * "ababc"
 * 返回值：
 * 3
 *
 * 说明：
 * 最长的回文子串为"aba"与"bab"，长度都为3
 * 示例2
 * 输入：
 * "abbba"
 * 返回值：
 * 5
 *
 * 示例3
 * 输入：
 * "b"
 * 返回值：
 * 1
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 最长回文子串 {

    public static void main(String[] args) {

    }

    /**
     * 这个题目用到了动态规划的思想具体
     * 注意字符串的遍历顺序一定是从后向前的，因为这样才能解决之前没有计算而直接出答案的问题。这里的dp数组比较难想，
     * 是应该存储所经过的子字符串是否是回文数
     */
    private static class EasySolution {

        public static int getLongestPalindrome(String A, int n) {
            // write code here
            char[] aa = A.toCharArray();
            int max = 1;
            boolean[][] dp = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }
            for (int i = 1; i < n; i++)//i指向的是字符的最后一位
                for (int j = i - 1; j >= 0; j--) {//j指向的是字符的前部。
                    if (i - j == 1) {//当两个指针靠近时，直接判断
                        dp[j][i] = (aa[i] == aa[j]);
                        if (max < i - j + 1)
                            max = i - j + 1;
                    } else {
                        if (dp[j + 1][i - 1] && aa[i] == aa[j]) {
                            dp[j][i] = true;
                            if (max < i - j + 1)
                                max = i - j + 1;
                        } else
                            dp[j][i] = false;
                    }
                }
            return max;
        }

    }

    private static class HardSolution {

    }

}
