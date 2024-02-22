package com.ng.code.menu.栈堆队列;

import com.ng.code.util.Solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/basic-calculator-ii/
 * 原题描述:
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在[-231, 231- 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_基本计算器II {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {
        public int calculate(String s) {
            int n = s.length();
            Deque<Integer> deque = new ArrayDeque<>();
            char design = '+';
            int num = 0;
            for (int i = 0; i < n; i++) {
                char a = s.charAt(i);
                if (Character.isDigit(a)) {
                    num = num * 10 + a - '0';
                }
                if (!Character.isDigit(a) && a != ' ' || i == n - 1) {
                    switch (design) {
                        case '+':
                            deque.push(num);
                            break;
                        case '-':
                            deque.push(-num);
                            break;
                        case '*':
                            int x = deque.pop();
                            deque.push(num * x);
                            break;
                        default:
                            deque.push(deque.pop() / num);
                            break;
                    }
                    design = a;
                    num = 0;
                }
            }
            int res = 0;
            while (!deque.isEmpty()) {
                res += deque.pop();
            }
            return res;
        }
    }

    private static class HardSolution {

    }

}
