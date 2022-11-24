package com.ng.code.menu.字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/group-anagrams/
 * 原题描述:
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * 示例:
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_字母异位词分组 {

    public static void main(String[] args) {
        String[] data = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        //[["bat"],["nat","tan"],["ate","eat","tea"]]
        LogUtil.pring(EasySolution.groupAnagrams(data).toString());
    }

    private static class EasySolution {
        /**
         * 计数法
         * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，
         * 故可以将每个字母出现的次数使用字符串表示，作为哈希表的键,比如a1e1t1
         * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26的数组记录每个字母出现的次数。
         * 需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。
         */
        public static List<List<String>> groupAnagrams(String[] strs) {

            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                int[] counts = new int[26];
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    counts[str.charAt(i) - 'a']++;
                }
                // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        sb.append((char) ('a' + i));
                        sb.append(counts[i]);
                    }
                }
                String key = sb.toString();
                List<String> list = map.get(key);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }

    private static class HardSolution {

    }

}
