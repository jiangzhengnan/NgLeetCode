package com.ng.code.menu.数字;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/happy-number/
 * 原题描述:
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * 示例:
 * 示例 1：
 * <p>
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_快乐数 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    /**
     * 破解一切死循环的办法，就是快慢指针
     */
    private static class EasySolution {

        public int squareSum(int n) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            return sum;
        }

        public boolean isHappy(int n) {
            int slow = n, fast = squareSum(n);
            while (slow != fast) {
                slow = squareSum(slow);
                fast = squareSum(squareSum(fast));
            }
            return slow == 1;
        }

    }

    private static class HardSolution {

    }

}
