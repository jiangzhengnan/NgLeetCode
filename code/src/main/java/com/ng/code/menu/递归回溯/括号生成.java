package com.ng.code.menu.递归回溯;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c9addb265cdf4cdd92c092c655d164ca?tpId=295&tqId=725&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
 * 例如，给出n=3，解集为：
 * "((()))", "(()())", "(())()", "()()()", "()(())"
 * 数据范围：1 \le n \le 81≤n≤8
 *
 * 示例:
 * 示例1
 * 输入：
 * 1
 * 返回值：
 * ["()"]
 *
 * 示例2
 * 输入：
 * 2
 * 返回值：
 * ["(())","()()"]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 括号生成 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.generateParenthesis(2).toString());
    }

    /**
     * 1. 如果左括号数量不大于 n，我们可以放一个左括号。
     * 2. 如果右括号数量小于左括号的数量，我们可以放一个右括号.
     */
    private static class EasySolution {

        static ArrayList<String> res = new ArrayList<>();
        static int n;

        public static ArrayList<String> generateParenthesis(int _n) {
            // write code here
            n = _n;
            dfs("", 0, 0);
            return res;
        }

        public static void dfs(String s, int cnt1, int cnt2) {
            if (cnt1 == n && cnt2 == n) {
                res.add(s);
                return;
            }
            if (cnt1 < n) {
                dfs(s + "(", cnt1 + 1, cnt2);
            }
            if (cnt1 > cnt2 && cnt2 < n) {
                dfs(s + ")", cnt1, cnt2 + 1);
            }
        }


    }

    private static class HardSolution {

    }

}
