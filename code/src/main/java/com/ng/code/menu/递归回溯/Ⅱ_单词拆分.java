package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/word-break/
 * 原题描述:
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 示例:
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_单词拆分 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        List<String> wordDict = new ArrayList<>();
        wordDict.add("go");
        wordDict.add("goal");
        wordDict.add("goals");
        wordDict.add("special");

        LogUtil.print(easySolution.wordBreak("goalspecial", wordDict));
    }

    private static class EasySolution {

        /**
         * 我们定义  dp[i] 表示字符串 s 前 i 个字符组成的字符串  s[0..i−1]
         * 是否能被空格拆分成若干个字典中出现的单词。
         */
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;//默认空串合法，0是空串
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }

    private static class HardSolution {

    }

}
