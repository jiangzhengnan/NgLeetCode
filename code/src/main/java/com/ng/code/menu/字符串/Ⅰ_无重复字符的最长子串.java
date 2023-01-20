package com.ng.code.menu.字符串;

import java.util.HashMap;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 原题描述:
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_无重复字符的最长子串 {

    public static void main(String[] args) {

    }

    private static class EasySolution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }
            if (s.length() <= 1) {
                return s.length();
            }
            char[] chars = s.toCharArray();
            int leftIndex = 0;
            int rightIndex = 0;
            int res = 0;

            int[] tong = new int[256];
            while (rightIndex < s.length()) {
                if (tong[chars[rightIndex]] == 0) {
                    tong[chars[rightIndex]] = 1;
                    res = Math.max(res, rightIndex - leftIndex + 1);
                    rightIndex++;
                } else {
                    tong = new int[256];
                    leftIndex++;
                    rightIndex = leftIndex;
                }
            }

            return res;
        }
    }

    /**
     * 这道题主要用到思路是：滑动窗口
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     * 时间复杂度：O(n)O(n)
     */
    private static class HardSolution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            int max = 0;
            int left = 0;
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    left = Math.max(left, map.get(s.charAt(i)) + 1);
                }
                map.put(s.charAt(i), i);
                max = Math.max(max, i - left + 1);
            }
            return max;

        }
    }

}
