package com.ng.code.menu.位运算;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/sum-of-two-integers/
 * 原题描述:
 * 给你两个整数 a 和 b ，不使用 运算符+ 和-，计算并返回两整数之和。
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 2
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 3
 * 输出：5
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_两整数之和 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    /**
     * 思路和算法
     * 虽然题目只要求了不能使用运算符  + 和  -，但是原则上来说也不宜使用类似的运算符
     * += 和  -= 以及  sum 等方法。于是，我们使用位运算来处理这个问题。
     * 首先，考虑两个二进制位相加的四种情况如下：
     * 0 + 0 = 0
     * 0 + 1 = 1
     * 1 + 0 = 1
     * 1 + 1 = 0 (进位)
     * 可以发现，对于整数 a  和 b ：
     * 在不考虑进位的情况下，其无进位加法结果为  a⊕b。
     * 而所有需要进位的位为 a&b，进位后的进位结果为  (a&b)<<1。
     * 于是，我们可以将整数 a  和 b  的和，拆分为 a  和 b 的无进位加法结果与进位结果的和。
     * 因为每一次拆分都可以让需要进位的最低位至少左移一位，又因为 a   和 b 可以取到负数，
     * 所以我们最多需要  log(max_int) 次拆分即可完成运算。
     */
    private static class EasySolution {
        public int getSum(int a, int b) {
            while (b != 0) {
                int carry = (a & b) << 1;
                a = a ^ b;
                b = carry;
            }
            return a;
        }
    }

    private static class HardSolution {

    }

}
