package com.ng.code.menu.字符串;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.LinkedList;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/decode-string/
 * 原题描述:
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_字符串解码 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        String data = "3[a2[c]]3[a]2[bc]";
        LogUtil.pring(easySolution.decodeString(data));

        LogUtil.pring(hardSolution.decodeString(data));

    }

    private static class EasySolution {
        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            LinkedList<Integer> stack_multi = new LinkedList<>();
            LinkedList<String> stack_res = new LinkedList<>();
            for (Character c : s.toCharArray()) {
                if (c == '[') {
                    stack_multi.addLast(multi);
                    stack_res.addLast(res.toString());
                    multi = 0;
                    res = new StringBuilder();
                } else if (c == ']') {
                    StringBuilder tmp = new StringBuilder();
                    int cur_multi = stack_multi.removeLast();
                    for (int i = 0; i < cur_multi; i++) {
                        tmp.append(res);
                    }
                    res = new StringBuilder(stack_res.removeLast() + tmp);
                } else if (c >= '0' && c <= '9') {
                    multi = multi * 10 + Integer.parseInt(c + "");
                } else {
                    res.append(c);
                }
            }
            return res.toString();
        }
    }

    private static class HardSolution {
        public String decodeString(String s) {
            return dfs(s, 0)[0];
        }

        private String[] dfs(String s, int i) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            while (i < s.length()) {
                char temp = s.charAt(i);
                if (temp >= '0' && temp <= '9')
                    multi = multi * 10 + Integer.parseInt(String.valueOf(temp));
                else if (temp == '[') {
                    String[] tmp = dfs(s, i + 1);
                    i = Integer.parseInt(tmp[0]);
                    while (multi > 0) {
                        res.append(tmp[1]);
                        multi--;
                    }
                } else if (temp == ']')
                    return new String[]{String.valueOf(i), res.toString()};
                else
                    res.append(String.valueOf(temp));
                i++;
            }
            return new String[]{res.toString()};
        }
    }

}
