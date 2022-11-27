package com.ng.code.menu.数字;

import java.util.HashMap;
import java.util.Map;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/fraction-to-recurring-decimal/
 * 原题描述:
 * 给定两个整数，分别表示分数的分子 Nodenumerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * <p>
 * 示例:
 * 示例 2：
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 * <p>
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_分数到小数 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        /**
         * 两者范围为 int，但计算结果可以会超过 int 范围，
         * 比如−2[31]  / -1 = 2[31]大于int最大值
         * 因此起始需要先使用 long 对两个入参类型转换一下
         */
        public String fractionToDecimal(int numerator, int denominator) {
            // 转 long 计算，防止溢出
            long a = numerator, b = denominator;
            // 如果本身能够整除，直接返回计算结果
            if (a % b == 0) {
                return String.valueOf(a / b);
            }
            StringBuilder sb = new StringBuilder();
            // 如果其一为负数，先追加负号
            if (a * b < 0) {
                sb.append('-');
            }
            a = Math.abs(a);
            b = Math.abs(b);
            // 计算小数点前的部分，并将余数赋值给 a
            sb.append(String.valueOf(a / b) + ".");
            a %= b;
            Map<Long, Integer> map = new HashMap<>();
            while (a != 0) {
                // 记录当前余数所在答案的位置，并继续模拟除法运算
                map.put(a, sb.length());
                a *= 10;
                sb.append(a / b);
                a %= b;
                // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
                if (map.containsKey(a)) {
                    int u = map.get(a);
                    return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
                }
            }
            return sb.toString();
        }

    }

    private static class HardSolution {

    }

}
