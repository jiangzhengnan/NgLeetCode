package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 1.模式中的字符'.'表示任意一个字符
 * 2.模式中的字符'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * 数据范围:
 * 1.str 只包含从 a-z 的小写字母。
 * 2.pattern 只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 3. 0 \le str.length \le 26 \0≤str.length≤26
 * 4. 0 \le pattern.length \le 26 \0≤pattern.length≤26
 *
 * 示例:
 * 示例1
 * 输入：
 * "aaa","a*a"
 * 返回值：
 * true
 *
 * 说明：
 * 中间的*可以出现任意次的a，所以可以出现1次a，能匹配上
 * 示例2
 * 输入：
 * "aad","c*a*d"
 * 返回值：
 * true
 *
 * 说明：
 * 因为这里 c 为 0 个，a被重复一次， * 表示零个或多个a。因此可以匹配字符串 "aad"。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 正则表达式匹配 {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * 为了方便，使用 ss 代指 str，使用 pp 代指 pattern。
     * 整理一下题意，对于字符串 p 而言，有三种字符：
     * 普通字符：需要和 s 中同一位置的字符完全匹配
     * '.'：能够匹配 s 中同一位置的任意字符
     * '*'：不能够单独使用 '*'，必须和前一个字符同时搭配使用，数据保证了 '*' 能够找到前面一个字符。能够匹配 s 中同一位置字符任意次。
     * 所以本题关键是分析当出现 a* 这种字符时，是匹配 0 个 a、还是 1 个 a、还是 2 个 a ...
     *
     * 本题可以使用动态规划进行求解：
     * 状态定义：f(i,j) 代表考虑 s 中以 i 为结尾的子串和 p 中的 j 为结尾的子串是否匹配。即最终我们要求的结果为 f[n][m] 。
     * 状态转移：也就是我们要考虑 f(i,j) 如何求得，前面说到了 p 有三种字符，所以这里的状态转移也要分三种情况讨论：
     * p[j] 为普通字符：匹配的条件是前面的字符匹配，同时 s 中的第 i 个字符和 p 中的第 j 位相同。 即 f(i,j) = f(i - 1, j - 1) && s[i] == p[j] 。
     * p[j] 为 '.'：匹配的条件是前面的字符匹配， s 中的第 i 个字符可以是任意字符。即 f(i,j) = f(i - 1, j - 1) && p[j] == '.'。
     * p[j] 为 '*'：读得 p[j - 1] 的字符，例如为字符 a。 然后根据 a* 实际匹配 s 中 a 的个数是 0 个、1 个、2 个 ...
     * 3.1. 当匹配为 0 个：f(i,j) = f(i, j - 2)
     * 3.2. 当匹配为 1 个：f(i,j) = f(i - 1, j - 2) && (s[i] == p[j - 1] || p[j - 1] == '.')
     * 3.3. 当匹配为 2 个：f(i,j) = f(i - 2, j - 2) && ((s[i] == p[j - 1] && s[i - 1] == p[j - 1]) || p[j] == '.')
     */
    private static class EasySolution {
        public boolean match(String ss, String pp) {
            // 技巧：往原字符头部插入空格，这样得到 char 数组是从 1 开始，而且可以使得 f[0][0] = true，可以将 true 这个结果滚动下去
            int n = ss.length(), m = pp.length();
            ss = " " + ss;
            pp = " " + pp;
            char[] s = ss.toCharArray();
            char[] p = pp.toCharArray();
            // f(i,j) 代表考虑 s 中的 1~i 字符和 p 中的 1~j 字符 是否匹配
            boolean[][] f = new boolean[n + 1][m + 1];
            f[0][0] = true;
            for (int i = 0; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // 如果下一个字符是 '*'，则代表当前字符不能被单独使用，跳过
                    if (j + 1 <= m && p[j + 1] == '*') continue;

                    // 对应了 p[j] 为普通字符和 '.' 的两种情况
                    if (i - 1 >= 0 && p[j] != '*') {
                        f[i][j] = f[i - 1][j - 1] && (s[i] == p[j] || p[j] == '.');
                    }

                    // 对应了 p[j] 为 '*' 的情况
                    else if (p[j] == '*') {
                        f[i][j] = (j - 2 >= 0 && f[i][j - 2]) || (i - 1 >= 0 && f[i - 1][j] && (s[i] == p[j - 1] || p[j - 1] == '.'));
                    }
                }
            }
            return f[n][m];
        }
    }

    private static class HardSolution {

    }

}
