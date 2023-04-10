package com.ng.code.menu.双指针;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
 * 字符串回文指该字符串正序与其逆序逐字符一致。
 *
 * 示例:
 * 输入：
 * "absba"
 * 返回值：
 * true
 */
@Solution(easy = 0, hard = 1, partice = 0)
public class Ⅰ_判断是否为回文字符串 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.judge("absba"));
    }

    private static class EasySolution {

        public static boolean judge(String str) {
            if (str == null || str.length() == 0) {
                return false;
            }
            int start = 0;
            int end = str.length() - 1;
            while (end > start) {
                if (str.charAt(start++) != str.charAt(end--)) {
                    return false;
                }
            }

            return true;
        }

    }

    private static class HardSolution {

    }

}
