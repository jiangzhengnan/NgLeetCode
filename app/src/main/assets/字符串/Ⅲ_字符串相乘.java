package com.ng.code.menu.字符串;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/multiply-strings/
 * 原题描述:
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例:
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_字符串相乘 {

    public static void main(String[] args) {

    }

    private static class EasySolution {
        /**
         * 计算形式
         *    num1
         *  x num2
         *  ------
         *  result
         */
        public static String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            // 保存计算结果
            String res = "0";

            // num2 逐位与 num1 相乘
            for (int i = num2.length() - 1; i >= 0; i--) {
                int carry = 0;
                // 保存 num2 第i位数字与 num1 相乘的结果
                StringBuilder temp = new StringBuilder();
                // 补 0
                for (int j = 0; j < num2.length() - 1 - i; j++) {
                    temp.append(0);
                }
                int n2 = num2.charAt(i) - '0';

                // num2 的第 i 位数字 n2 与 num1 相乘
                for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                    int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                    int product = (n1 * n2 + carry) % 10;
                    temp.append(product);
                    carry = (n1 * n2 + carry) / 10;
                }
                // 将当前结果与新计算的结果求和作为新的结果
                res = addStrings(res, temp.reverse().toString());
            }
            return res;
        }

        /**
         * 对两个字符串数字进行相加，返回字符串形式的和
         */
        public static String addStrings(String num1, String num2) {
            StringBuilder builder = new StringBuilder();
            int carry = 0;
            for (int i = num1.length() - 1, j = num2.length() - 1;
                 i >= 0 || j >= 0 || carry != 0;
                 i--, j--) {
                int x = i < 0 ? 0 : num1.charAt(i) - '0';
                int y = j < 0 ? 0 : num2.charAt(j) - '0';
                int sum = (x + y + carry) % 10;
                builder.append(sum);
                carry = (x + y + carry) / 10;
            }
            return builder.reverse().toString();
        }
    }

    private static class HardSolution {

    }

}
