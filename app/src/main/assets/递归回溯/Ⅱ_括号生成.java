package com.ng.code.menu.递归回溯;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.List;

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
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_括号生成 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        LogUtil.print(easySolution.generateParenthesis(3).toString());
    }

    /**
     * dfs
     */
    private static class EasySolution {

        ArrayList<String> res = new ArrayList<>();

        public ArrayList<String> generateParenthesis(int n) {
            // write code here
            dfs("", n, n);
            return res;
        }

        public void dfs(String s, int left, int right) {
            if (left == 0 && right == 0) {
                //左右括号用完了，加入结果
                res.add(s);
                return;
            }
            if (left > 0) {
                //若左边括号还有剩，加一个左括号，继续生成
                dfs(s + "(", left - 1, right);
            }
            if (left < right && right > 0) {
                //若已经增加的左括号多余右括号，加一个右括号，继续生成
                dfs(s + ")", left, right - 1);
            }
        }

    }

    /**
     * 回溯
     */
    private static class HardSolution {

        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<String>();
            backtrack(ans, new StringBuilder(), 0, 0, n);
            return ans;
        }

        public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
            if (cur.length() == max * 2) {
                ans.add(cur.toString());
                return;
            }
            if (open < max) {
                cur.append('(');
                backtrack(ans, cur, open + 1, close, max);
                cur.deleteCharAt(cur.length() - 1);
            }
            if (close < open) {
                cur.append(')');
                backtrack(ans, cur, open, close + 1, max);
                cur.deleteCharAt(cur.length() - 1);
            }
        }

    }

}
