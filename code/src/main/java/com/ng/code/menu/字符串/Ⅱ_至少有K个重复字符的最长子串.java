package com.ng.code.menu.字符串;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;

/**
 * 很好的思路！
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
 * 原题描述:
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_至少有K个重复字符的最长子串 {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        String s = "aaabb";
        LogUtil.pring(easySolution.longestSubstring(s, 3));
    }

    /**
     * 1 递归最基本的是记住递归函数的含义（务必牢记函数定义）：本题的 longestSubstring(s, k) 函数表示的就是题意，
     * 即求一个最长的子字符串的长度，该子字符串中每个字符出现的次数都最少为 kk。函数入参 s 是表示源字符串；kk 是限制条件，
     * 即子字符串中每个字符最少出现的次数；函数返回结果是满足题意的最长子字符串长度。
     * <p>
     * 2 递归的终止条件（能直接写出的最简单 case）：如果字符串 s 的长度少于 kk，那么一定不存在满足题意的子字符串，返回 0；
     * <p>
     * 3 调用递归（重点）：如果一个字符 c 在 s 中出现的次数少于 kk 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。
     * 所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果：把 s 按照 c 分割（分割后每个子串都不包含 c），
     * 得到很多子字符串 tt；下一步要求 tt 作为源字符串的时候，它的最长的满足题意的子字符串长度
     * （到现在为止，我们把大问题分割为了小问题(s → tt)）。此时我们发现，恰好已经定义了函数 longestSubstring(s, k)
     * 就是来解决这个问题的！所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归。
     * <p>
     * 4 未进入递归时的返回结果：如果 s 中的每个字符出现的次数都大于 kk 次，那么 s 就是我们要求的字符串，直接返回该字符串的长度。
     */
    private static class EasySolution {

        @RequiresApi(api = Build.VERSION_CODES.N)
        public int longestSubstring(String s, int k) {
            //1 终止条件
            if (s.length() < k) return 0;

            HashMap<Character, Integer> counter = new HashMap();
            for (int i = 0; i < s.length(); i++) {
                counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
            }
            for (char c : counter.keySet()) {
                //如果一个字符 c  在 s  中出现的次数少于 kk 次
                if (counter.get(c) < k) {
                    int res = 0;
                    for (String t : s.split(String.valueOf(c))) {
                        res = Math.max(res, longestSubstring(t, k));
                    }
                    return res;
                }
            }
            // 4.如果 s 中的每个字符出现的次数都大于 kk 次，那么 s 就是我们要求的字符串，直接返回该字符串的长度。
            return s.length();
        }
    }

    private static class HardSolution {

    }

}
