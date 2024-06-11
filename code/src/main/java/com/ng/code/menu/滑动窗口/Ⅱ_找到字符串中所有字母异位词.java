package com.ng.code.menu.滑动窗口;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.airbnb.lottie.L;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
 * 原题描述:
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
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
        public List<Integer> findAnagrams(String s, String t) {
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            int left = 0, right = 0;
            int valid = 0;
            List<Integer> res = new ArrayList<>(); // 记录结果
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }
                // 判断左侧窗口是否要收缩
                while (right - left >= t.length()) {
                    // 当窗口符合条件时，把起始索引加入 res
                    if (valid == need.size()) {
                        res.add(left);
                    }
                    char d = s.charAt(left);
                    left++;
                    // 进行窗口内数据的一系列更新
                    if (need.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            valid--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }
            return res;
        }

    }

}
