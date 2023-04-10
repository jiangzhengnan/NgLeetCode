package com.ng.code.menu.数字;

import java.util.Deque;
import java.util.LinkedList;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 * 原题描述:
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括  +、-、*、/  。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 注意  两个整数之间的除法只保留整数部分。
 * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例:
 * 示例  1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例  2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_逆波兰表达式求值 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        LogUtil.print(easySolution.evalRPN(tokens));

    }

    private static class EasySolution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new LinkedList<Integer>();
            int n = tokens.length;
            for (int i = 0; i < n; i++) {
                String token = tokens[i];
                if (isNumber(token)) {
                    stack.push(Integer.parseInt(token));
                } else {
                    int num2 = stack.pop();
                    int num1 = stack.pop();
                    switch (token) {
                        case "+":
                            stack.push(num1 + num2);
                            break;
                        case "-":
                            stack.push(num1 - num2);
                            break;
                        case "*":
                            stack.push(num1 * num2);
                            break;
                        case "/":
                            stack.push(num1 / num2);
                            break;
                        default:
                    }
                }
            }
            return stack.pop();
        }

        public boolean isNumber(String token) {
            return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
        }
    }

    private static class HardSolution {

    }

}
