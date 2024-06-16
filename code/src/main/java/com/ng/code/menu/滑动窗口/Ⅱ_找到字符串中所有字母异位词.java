package com.ng.code.menu.滑动窗口;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
 * 原题描述:
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅱ_找到字符串中所有字母异位词 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        LogUtil.print(easySolution.findAnagrams("cbaebabacd", "abc").toString());
    }

    private static class EasySolution {

        /**
         * 找到字符符号，比如abc可以表示为011121
         *
         * @param s
         * @param p
         * @return
         */
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> result = new ArrayList<>();

            String targetKey = getTargetKey(p);
            for (int i = 0; i <= s.toCharArray().length - p.length(); i++) {
                String tempKey = getTargetKey(s.substring(i, i + p.length()));
                if (Objects.equals(tempKey, targetKey)) {
                    result.add(i);
                }
            }
            return result;
        }

        private String getTargetKey(String p) {
            char[] charArray = p.toCharArray();
            int[] tong = new int[26];
            for (char temp : charArray) {
                tong[temp - 'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < tong.length; i++) {
                if (tong[i] > 0) {
                    key.append("").append(i).append(tong[i]);
                }
            }
            return key.toString();
        }
    }

    private static class HardSolution {

        /**
         * 滑动窗口
         */
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();

            int n = p.length();
            if (s.length() < n) {
                return res;
            }
            int[] sList = new int[26];
            int[] pList = new int[26];
            for (int i = 0; i < n; i++) {
                sList[s.charAt(i) - 'a']++;
                pList[p.charAt(i) - 'a']++;
            }
            if (Arrays.equals(sList, pList)) {
                res.add(0);
            }

            int left = 0;
            int right = n - 1;
            while (right + 1 < s.length()) {
                sList[s.charAt(left) - 'a']--;
                left++;
                right++;
                sList[s.charAt(right) - 'a']++;
                if (Arrays.equals(sList, pList)) {
                    res.add(left);
                }
            }

            return res;
        }

    }

}
