package com.ng.code.menu.字符串;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * 要求时间复杂度On
 *
 * 示例:
 * 示例1
 * 输入：
 * "1","99"
 * 返回值：
 * "100"
 * 复制
 * 说明：
 * 1+99=100
 */
@Solution(easy = 1, hard = 1, partice = 0)
public class Ⅱ_大数加法 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.solve("1", "99"));
    }

    //栈
    private static class EasySolution {

        public static String solve(String s, String t) {
            // write code here
            Stack<Integer> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            int wei = 1;
            int left = 0, right = 0;
            int add = 0;
            while (s.length() >= wei || t.length() >= wei) {
                if (s.length() >= wei) {
                    left = Integer.parseInt(s.substring(s.length() - wei, s.length() - wei + 1));
                }

                if (t.length() >= wei) {
                    right = Integer.parseInt(t.substring(t.length() - wei, t.length() - wei + 1));
                }
                stack.push((add + left + right) % 10);
                add = (left + right + add) >= 10 ? 1 : 0;

                left = 0;
                right = 0;
                wei++;
            }
            if (add == 1) {
                stack.push(1);
            }

            while (stack.size() != 0) {
                sb.append(stack.pop());
            }

            return sb.toString();
        }

    }

    private static class HardSolution {

    }

}
