package com.ng.code.menu.字符串;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link: https://leetcode.cn/problems/longest-common-prefix/description/
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅰ_最长公共前缀 {

    public static void main(String[] args) {
        String[] data = new String[]{"abca", "abc", "abca", "abc", "abcc"};
        String[] dat2 = new String[]{"a"};

        LogUtil.print(EasySolution.longestCommonPrefix(dat2));
    }

    private static class EasySolution {

        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0 || strs[0].equals("")) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int startIndex = 0;
            char nowTemp = strs[0].charAt(0);
            while (true) {
                for (String temp : strs) {
                    if (temp.equals("") || temp.length() < startIndex + 1 || temp.charAt(startIndex) != nowTemp) {
                        return sb.toString();
                    }
                }
                sb.append(strs[0].charAt(startIndex));
                startIndex++;
                if (startIndex < strs[0].length()) {
                    nowTemp = strs[0].charAt(startIndex);
                }
            }
        }
    }

    /**
     * 将字符串数组看作一个二维空间，每一次从第一列开始。
     * 确定所有字符子串中第一列字符。
     * 逐层扫描后面每一列，遇到不同字符停止扫描。
     */
    private static class HardSolution {
        public String longestCommonPrefix(String[] strs) {
            // //纵向扫描
            if (strs.length == 0 || strs == null) {
                return "";
            }

            int rows = strs.length;
            int cols = strs[0].length();
            //开始扫描
            for (int i = 0; i < cols; i++) {
                char firstChar = strs[0].charAt(i);
                for (int j = 1; j < rows; j++) {
                    if (strs[j].length() == i || strs[j].charAt(i) != firstChar) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }
    }

}
