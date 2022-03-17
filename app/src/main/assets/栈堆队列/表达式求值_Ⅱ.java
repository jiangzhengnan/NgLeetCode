package com.ng.code.menu.栈堆队列;

import com.ng.code.util.Solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c215ba61c8b1443b996351df929dc4d4?tpId=295&tqId=1076787&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 请写一个整数计算器，支持加减乘三种运算和括号。
 * 数据范围：0\le |s| \le 1000≤∣s∣≤100，保证计算结果始终在整型范围内
 * 要求：空间复杂度： O(n)O(n)，时间复杂度 O(n)O(n)
 * 示例:
 * 示例1
 * 输入：
 * "1+2"
 * 返回值：
 * 3
 * 示例2
 * 输入：
 * "(2*(3-4))*5"
 * 返回值：
 * -10
 * 示例3
 * 输入：
 * "3+2*3*4-1"
 * 返回值：
 * 26
 */
@Solution(easy = 0, hard = 0)
public class 表达式求值_Ⅱ {

    public static void main(String[] args) {

    }

    /**
     * 算法：栈
     * 1.用栈保存各部分计算的和
     * 2.遍历表达式，使用 sign 变量记录运算符，初始化是 '+'；使用 number 变量记录字符串中的数字部分的数字值是多少
     * 2.0 遇到空格时跳过
     * 2.1 遇到数字时继续遍历求这个完整的数字的值，保存到 number 中
     * 2.2 遇到左括号时递归求这个括号里面的表达式的值
     * 先遍历找到对应的右括号，因为可能里面还嵌有多对括号，使用一个变量 counterPartition 统计括号对数直到变量为 0
     * 2.3 遇到运算符时或者到表达式末尾时，就去计算上一个运算符并把计算结果 push 进栈，然后保存新的运算符到 sign
     * 如果是 + ，不要计算，push 进去
     * 如果是 - ，push 进去负的当前数
     * 如果是 ×、÷ ，pop 出一个运算数和当前数作计算
     * 3.最后把栈中的结果求和即可
     */
    private static class EasySolution {

        public int calculate(String s) {
            s = s.trim();
            Deque<Integer> stack = new ArrayDeque<>();
            int number = 0;
            char sign = '+';
            char[] charArray = s.toCharArray();
            for (int i = 0, n = charArray.length; i < n; i++) {
                char c = charArray[i];
                if (c == ' ') {
                    continue;
                }
                if (Character.isDigit(c)) {
                    number = number * 10 + c - '0';
                }
                if (c == '(') {
                    int j = i + 1;
                    int counterPartition = 1;
                    while (counterPartition > 0) {
                        if (charArray[j] == '(') {
                            counterPartition++;
                        }
                        if (charArray[j] == ')') {
                            counterPartition--;
                        }
                        j++;
                    }
                    number = calculate(s.substring(i + 1, j - 1));
                    i = j - 1;
                }
                if (!Character.isDigit(c) || i == n - 1) {
                    if (sign == '+') {
                        stack.push(number);
                    } else if (sign == '-') {
                        stack.push(-1 * number);
                    } else if (sign == '*') {
                        stack.push(stack.pop() * number);
                    } else if (sign == '/') {
                        stack.push(stack.pop() / number);
                    }
                    number = 0;
                    sign = c;
                }
            }
            int ans = 0;
            while (!stack.isEmpty()) {
                ans += stack.pop();
            }
            return ans;
        }

    }

    private static class HardSolution {

    }

}
