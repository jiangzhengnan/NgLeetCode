package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 给定两个字符串 str1 和 str2 ，请你算出将 str1 转为 str2 的最少操作数。
 * 你可以对字符串进行3种操作：
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.修改一个字符。
 * 字符串长度满足 1 \le n \le 1000 \1≤n≤1000  ，保证字符串中只出现小写英文字母。
 *
 * 示例:
 * 示例1
 * 输入：
 * "nowcoder","new"
 * 返回值：
 * 6
 *
 * 说明：
 * "nowcoder"=>"newcoder"(将'o'替换为'e')，修改操作1次
 * "nowcoder"=>"new"(删除"coder")，删除操作5次
 * 示例2
 * 输入：
 * "intention","execution"
 * 返回值：
 * 5
 *
 * 说明：
 * 一种方案为:
 * 因为2个长度都是9，后面的4个后缀的长度都为"tion"，于是从"inten"到"execu"逐个修改即可
 * 示例3
 * 输入：
 * "now","nowcoder"
 * 返回值：
 * 5
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_编辑距离一 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        LogUtil.print(easySolution.minDistance("horse", "ros"));

    }

    /**
     * 动态规划（空间压缩）
     * 在状态转移方程中我们可以看到，长度为i的字符串的编辑距离，只和长度为i-1或长度为i的字符串相关，
     * 因此可以使用两个一维数组替代二维数组，实现空间压缩，两个一维数组分别表示长度为i时的编辑距离和i-1时的编辑距离。
     */
    private static class EasySolution {

        //https://leetcode.cn/problems/edit-distance/solutions/6455/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/?envType=study-plan-v2&envId=top-interview-150
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            //第一列
            for (int i = 1; i <= m; i++) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
            //第一行
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j - 1] + 1;
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    char c1 = word1.charAt(i - 1);
                    char c2 = word2.charAt(j - 1);
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                }
            }
            return dp[m][n];
        }

    }

    private static class HardSolution {

    }

}
