package com.ng.code.menu.递归回溯;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=295&tqId=23291&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 描述
 * 输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
 * 例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
 *
 * 示例:
 * 示例1
 * 输入：
 * "ab"
 * 返回值：
 * ["ab","ba"]
 *
 * 说明：
 * 返回["ba","ab"]也是正确的
 * 示例2
 * 输入：
 * "aab"
 * 返回值：
 * ["aab","aba","baa"]
 *
 * 示例3
 * 输入：
 * "abc"
 * 返回值：
 * ["abc","acb","bac","bca","cab","cba"]
 *
 * 示例4
 * 输入：
 * ""
 * 返回值：
 * []
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_字符串的排列 {

    public static void main(String[] args) {
        String data = "abc";
        LogUtil.print(HardSolution.Permutation(data).toString());
    }

    /**
     * 用回溯思想做,结果用Set去重
     */
    private static class EasySolution {

        static Set<String> res = new HashSet<>();

        public static ArrayList<String> Permutation(String str) {
            boolean[] visited = new boolean[str.length()];
            recursion(str, "", visited);
            ArrayList<String> list = new ArrayList<>(res);
            Collections.sort(list);
            return list;
        }

        public static void recursion(String str, String current, boolean[] visited) {
            if (str.length() == current.length()) {
                res.add(current);
                return;
            }
            for (int i = 0; i < str.length(); i++) {
                if (!visited[i]) {
                    current += str.charAt(i);
                    visited[i] = true;
                    recursion(str, current, visited);
                    current = current.substring(0, current.length() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    private static class HardSolution {

        public static ArrayList<String> Permutation(String str) {
            ArrayList<String> result = new ArrayList<>();
            if (str.length() == 0) {
                return result;
            }

            recur(str, "", result);
            return result;
        }

        /**
         * @param str    当前我们剩下可以取的string
         * @param cur    即我们当前所拥有的字符串
         * @param result 是我们符合条件的字符串的合集。
         */
        public static void recur(String str, String cur, ArrayList<String> result) {
            if (str.length() == 0) {
                if (!result.contains(cur)) {
                    LogUtil.print("添加合集:" + cur);
                    result.add(cur);
                }
            }
            for (int i = 0; i < str.length(); i++) {
                //每次取第i个作为当前拥有的，然后str为i之前的和i之后的相加
                String str1 = str.substring(0, i);
                String str2 = str.substring(i + 1, str.length());
                String newStr = str1 + str2;
                recur(newStr, cur + str.charAt(i), result);
            }
        }
    }

}
