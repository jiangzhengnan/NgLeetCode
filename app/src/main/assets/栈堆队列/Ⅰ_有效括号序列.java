package com.ng.code.menu.栈堆队列;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/37548e94a270412c8b9fb85643c8ccc2?tpId=295&tqId=726&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 *
 * 数据范围：字符串长度 0\le n \le 100000≤n≤10000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 */
@Solution(easy = 0, hard = 1)
public class Ⅰ_有效括号序列 {

    public static void main(String[] args) {
        String data = "([)]";
        String data2 = "[()]";
        LogUtil.pring(EasySolution.isValid(data));
        LogUtil.pring(EasySolution.isValid(data2));
    }

    /**
     * 用栈
     */
    private static class EasySolution {
        public static boolean isValid(String s) {
            Stack<Character> data = new Stack<>();
            char[] sList = s.toCharArray();
            for (char temp : sList) {
                if (data.isEmpty()) {
                    data.push(temp);
                } else {
                    char nowTop = data.peek();
                    if ((nowTop == '[' && temp == ']') ||
                            (nowTop == '(' && temp == ')') ||
                            ((nowTop == '{' && temp == '}'))) {
                        data.pop();
                    } else {
                        data.push(temp);
                    }
                }
            }
            return data.isEmpty();
        }
    }

    /**
     * 同上
     */
    private static class HardSolution {

    }

}
