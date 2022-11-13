package com.ng.code.menu.字符串;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * https://leetcode.cn/problems/string-to-integer-atoi/
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231, 231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231− 1 。
 * 返回整数作为最终结果。
 * <p>
 * 示例:
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："42"（读入 "42"）
 * ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_字符串转换整数 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.myAtoi(" -21"));

    }

    private static class EasySolution {
        public static int myAtoi(String s) {
            int sign = 1;
            int res = 0;
            int m = s.length();
            int i = 0;
            while (i < m && s.charAt(i) == ' ') {
                i++;
            }
            int start = i;
            for (; i < m; i++) {
                char c = s.charAt(i);
                if (i == start && c == '+') {
                    sign = 1;
                } else if (i == start && c == '-') {
                    sign = -1;
                } else if (Character.isDigit(c)) {
                    int num = c - '0';
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                        return Integer.MAX_VALUE;
                    }

                    if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                        return Integer.MIN_VALUE;
                    }
                    res = res * 10 + sign * num;
                } else {
                    break;
                }
            }
            return res;
        }
    }

    private static class HardSolution {

    }

}
