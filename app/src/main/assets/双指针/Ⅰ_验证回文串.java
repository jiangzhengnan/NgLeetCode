package com.ng.code.menu.双指针;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link: https://leetcode.cn/problems/XltzEq/description/
 * 原题描述:
 * 给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
 * 字符串回文指该字符串正序与其逆序逐字符一致。
 * <p>
 * 示例:
 * 输入：
 * "absba"
 * 返回值：
 * true
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_验证回文串 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.isPalindrome("absba"));
    }

    private static class EasySolution {

        public static boolean isPalindrome(String s) {
            StringBuilder sb = new StringBuilder();
            for (char temp : s.toCharArray()) {
                if ((temp >= 'a' && temp <= 'z') ||
                    (temp >= 'A' && temp <= 'Z') ||
                    (temp >= '0' && temp <= '9')) {
                    sb.append(temp);
                }
            }
            int n = sb.length();
            for (int i = 0; i < n / 2; i++) {
                if (!String.valueOf(sb.charAt(i))
                    .equalsIgnoreCase(String.valueOf(sb.charAt(n - i - 1)))) {
                    return false;
                }

            }

            return true;
        }

    }

    private static class HardSolution {

    }

}
