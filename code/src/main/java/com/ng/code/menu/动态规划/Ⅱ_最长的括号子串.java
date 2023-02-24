package com.ng.code.menu.动态规划;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * 原题描述:
 * https://leetcode.cn/problems/longest-valid-parentheses/
 *
 * 给出一个长度为 n 的，仅包含字符 '(' 和 ')' 的字符串，计算最长的格式正确的括号子串的长度。
 * 例1: 对于字符串 "(()" 来说，最长的格式正确的子串是 "()" ，长度为 2 .
 * 例2：对于字符串 ")()())" , 来说, 最长的格式正确的子串是 "()()" ，长度为 4 .
 * 要求时间复杂度 O(n)O(n) ,空间复杂度 O(n)O(n).
 *
 * 示例:
 * 示例1
 * 输入：
 * "(()"
 * 返回值：
 * 2
 *
 * 示例2
 * 输入：
 * "(())"
 * 返回值：
 * 4
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_最长的括号子串 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.longestValidParentheses(")()(())"));
    }

    private static class EasySolution {

        public static int longestValidParentheses(String s) {
            // write code here
            int n = s.length();
            if (n <= 1) {
                return 0;
            }
            int max = 0;
            char[] c = s.toCharArray();
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < n; i++) {
                if (c[i] == '(') {
                    stack.push(i);  //push的是坐标
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);        //表示能匹配的左括号前面的那个元素
                    } else {
                        max = Math.max(max, i - stack.peek());  //stack 保存的是最后一个被匹配的’(‘的前面的索引                }
                    }
                }
            }
            return max;
        }

    }

    private static class HardSolution {

    }

}
