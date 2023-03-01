package com.ng.code.menu.递归回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/palindrome-partitioning/
 * 原题描述:
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例:
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_分割回文串 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        LogUtil.print(easySolution.partition("cdd").toString());
    }

    /**
     * 如果前缀字符串是回文，则可以产生分支和结点；
     * 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
     */
    private static class EasySolution {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
            // 注意：只使用 stack 相关的接口
            Deque<String> stack = new ArrayDeque<>();
            char[] charArray = s.toCharArray();
            dfs(charArray, 0, len, stack, res);
            return res;
        }

        /**
         * @param charArray
         * @param index     起始字符的索引
         * @param len       字符串 s 的长度，可以设置为全局变量
         * @param path      记录从根结点到叶子结点的路径
         * @param res       记录所有的结果
         */
        private void dfs(char[] charArray, int index, int len, Deque<String> path, List<List<String>> res) {
            if (index == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = index; i < len; i++) {
                // 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
                /**
                 * 产生前缀字符串的时候，判断前缀字符串是否是回文。
                 * 如果前缀字符串是回文，则可以产生分支和结点；
                 * 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作。
                 */
                if (!checkPalindrome(charArray, index, i)) {
                    continue;
                }
                path.addLast(new String(charArray, index, i + 1 - index));
                dfs(charArray, i + 1, len, path, res);
                path.removeLast();
            }
        }

        /**
         * 这一步的时间复杂度是 O(N)，优化的解法是，先采用动态规划，把回文子串的结果记录在一个表格里
         *
         * @param charArray
         * @param left      子串的左边界，可以取到
         * @param right     子串的右边界，可以取到
         * @return
         */
        private boolean checkPalindrome(char[] charArray, int left, int right) {
            while (left < right) {
                if (charArray[left] != charArray[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    /**
     * 回溯的优化（使用动态规划得到所有子串是否是回文）
     */
    private static class HardSolution {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            char[] charArray = s.toCharArray();
            // 预处理
            // 状态：dp[i][j] 表示 s[i][j] 是否是回文
            boolean[][] dp = new boolean[len][len];
            // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
            for (int right = 0; right < len; right++) {
                // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
                for (int left = 0; left <= right; left++) {
                    if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            Deque<String> stack = new ArrayDeque<>();
            dfs(s, 0, len, dp, stack, res);
            return res;
        }

        private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
            if (index == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    path.addLast(s.substring(index, i + 1));
                    dfs(s, i + 1, len, dp, path, res);
                    path.removeLast();
                }
            }
        }
    }

}
