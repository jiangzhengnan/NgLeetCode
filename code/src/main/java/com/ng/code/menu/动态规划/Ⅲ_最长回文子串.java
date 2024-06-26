package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
 * 原题描述:
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 * 进阶:  空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * <p>
 * 示例:
 * 示例1
 * 输入：
 * "ababc"
 * 返回值：
 * 3
 * <p>
 * 说明：
 * 最长的回文子串为"aba"与"bab"，长度都为3
 * 示例2
 * 输入：
 * "abbba"
 * 返回值：
 * 5
 * <p>
 * 示例3
 * 输入：
 * "b"
 * 返回值：
 * 1
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_最长回文子串 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        LogUtil.print(easySolution.longestPalindrome("babad"));

    }

    private static class EasySolution {

        /**
         * 从中心向两端扩散的双指针技巧。
         * for 0 <= i < len(s):
         * 找到以 s[i] 为中心的回文串
         * 找到以 s[i] 和 s[i+1] 为中心的回文串
         * 更新答案
         */
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                // 以 s[i] 为中心的最长回文子串
                String s1 = palindrome(s, i, i);
                // 以 s[i] 和 s[i+1] 为中心的最长回文子串
                String s2 = palindrome(s, i, i + 1);
                // res = longest(res, s1, s2)
                res = res.length() > s1.length() ? res : s1;
                res = res.length() > s2.length() ? res : s2;
            }
            return res;
        }


        // 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
        public String palindrome(String s, int l, int r) {
            // 防止索引越界
            while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
                // 双指针，向两边展开
                l--;
                r++;
            }
            // 返回以 s[l] 和 s[r] 为中心的最长回文串
            return s.substring(l + 1,
                r); //这里为什么要+1?因为只有 (l,r)范围内的是符合要求的回文串，因为substring是[_,_)，所以l需要+1
        }


    }

    private static class HardSolution {

        /**
         * 动态规划
         * 对于一个子串而言，如果它是回文串，并且长度大于 22，那么将它首尾的两个字母去除之后，它仍然是个回文串。
         * 例如对于字符串  “ababa”，如果我们已经知道 bab” 是回文串，那么  “ababa” 一定是回文串，
         * 这是因为它的首尾两个字母都是  “a”。
         * 根据这样的思路，我们就可以用动态规划的方法解决本题。
         * 我们用 P(i,j)P(i,j) 表示字符串 ss 的第 ii 到 jj 个字母组成的串（下文表示成 s[i:j]s[i:j]）是否为回文串：
         * P(i,j)=P(i+1,j−1) (Si ==  Sj)
         */
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2) {
                return s;
            }

            int maxLen = 1;
            int begin = 0;
            // dp[i][j] 表示 s[i..j] 是否是回文串
            boolean[][] dp = new boolean[len][len];
            // 初始化：所有长度为 1 的子串都是回文串
            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }

            char[] charArray = s.toCharArray();
            // 递推开始
            // 先枚举子串长度
            for (int L = 2; L <= len; L++) {
                // 枚举左边界，左边界的上限设置可以宽松一些
                for (int i = 0; i < len; i++) {
                    // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                    int j = L + i - 1;
                    // 如果右边界越界，就可以退出当前循环
                    if (j >= len) {
                        break;
                    }

                    if (charArray[i] != charArray[j]) {
                        dp[i][j] = false;
                    } else {
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLen);
        }
    }

}
