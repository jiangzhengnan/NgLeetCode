package com.ng.code.menu.位运算;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * https://leetcode.cn/problems/counting-bits/?favorite=2cktkvj
 * 原题描述:
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * 示例:
 * <p>
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅰ_比特位计数 {

    public static void main(String[] args) {

    }

    /**
     * 最直观的做法是对从 00 到 nn 的每个整数直接计算「一比特数」。每个 int 型的数都可以用 3232 位二进制数表示，
     * 只要遍历其二进制表示的每一位即可得到 11 的数目。
     * <p>
     * 利用 {Brian Kernighan}BrianKernighan 算法，可以在一定程度上进一步提升计算速度。{Brian Kernighan}BrianKernighan
     * 算法的原理是：对于任意整数 xx，令 x=x&(x−1)，
     * 比如3 & 2 = 11 & 10 = 10;
     * 4&3 = 100 & 11 = 11;
     * 5&4 = 101 & 100 = 100;
     * 该运算将 xx 的二进制表示的最后一个 1 变成 0。因此，对 xx 重复该操作，直到 xx 变成 00，则操作次数即为 xx 的「一比特数」。
     * <p>
     * 对于给定的 nn，计算从 00 到 nn 的每个整数的「一比特数」的时间都不会超过 O(\log n)O(logn)，因此总时间复杂度为 O(n \log n)O(nlogn)。
     */
    private static class EasySolution {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                bits[i] = countOnes(i);
            }
            return bits;
        }

        public int countOnes(int x) {
            int ones = 0;
            while (x > 0) {
                x &= (x - 1);
                ones++;
            }
            return ones;
        }
    }

    /**
     * 动态规划
     * 对于所有的数字，只有两类：
     * <p>
     * 奇数：
     * 二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     * 举例：
     * 0 = 0       1 = 1
     * 2 = 10      3 = 11
     * 偶数：
     * 二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。
     * 因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     * 举例：
     * 2 = 10       4 = 100       8 = 1000
     * 3 = 11       6 = 110       12 = 1100
     * 另外，0 的 1 个数为 0，于是就可以根据奇偶性开始遍历计算了。
     */
    private static class HardSolution {
        public int[] countBits(int n) {
            int[] result = new int[n + 1];
            result[0] = 0;
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    result[i] = result[i - 1] + 1;
                } else {
                    result[i] = result[i / 2];
                }
            }

            return result;
        }
    }

}
