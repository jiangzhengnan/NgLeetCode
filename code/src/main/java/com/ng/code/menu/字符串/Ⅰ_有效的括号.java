package com.ng.code.menu.字符串;

import java.util.Stack;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link: https://leetcode.cn/problems/valid-parentheses/description/?envType=study-plan-v2&envId
 * =top-100-liked
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_有效的括号 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        LogUtil.print(EasySolution.isValid("]"));
    }

    private static class EasySolution {

        // ([{}])
        public static boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            for (char temp : s.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(temp);
                } else if ((temp == ')' && stack.peek() == '(') ||
                    (temp == ']' && stack.peek() == '[') ||
                    (temp == '}' && stack.peek() == '{')
                ) {
                    stack.pop();
                } else {
                    stack.push(temp);
                }
            }
            return stack.isEmpty();
        }

    }

    private static class HardSolution {

    }

}
