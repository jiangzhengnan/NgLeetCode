package com.ng.code.menu.双指针;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
 * 空间复杂度 O(n) ，时间复杂度 O(n)
 *
 * 示例:
 * 输入：
 * "abcd"
 * 返回值：
 * "dcba"
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_反转字符串 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.solve("abcd"));
    }

    private static class EasySolution {

        public static String solve(String str) {
            // write code here
            char[] reveerList = new char[str.length()];
            for (int i = 0; i < str.toCharArray().length; i++) {
                reveerList[str.length() - i - 1] = str.toCharArray()[i];
            }
            return new String(reveerList);
        }

    }

    //原地交换
    private static class HardSolution {

        public String solve(String str) {
            char[] cstr = str.toCharArray();
            int len = str.length();
            for (int i = 0; i < len / 2; i++) {
                char t = cstr[i];
                cstr[i] = cstr[len - 1 - i];
                cstr[len - 1 - i] = t;
            }
            return new String(cstr);
        }

    }

}
