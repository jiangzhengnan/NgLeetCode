package com.ng.code.menu.位运算;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/reverse-bits/?favorite=2ckc81c
 * 原题描述:
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * 示例:
 * 示例 1：
 * 输入：n =        00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_颠倒二进制位 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        String hex = "00000010100101000001111010011100";
        //LogUtil.pring(Integer.parseInt(hex, 2));

        int result = easySolution.reverseBits(Integer.parseInt(hex, 2));
        LogUtil.pring(Integer.toString(result, 2));

    }

    /**
     * ans 每次最后一位被赋值后，就左移一位，一直移动32次。num 同样每次取最后一位，最终向右移动32位。
     */
    private static class EasySolution {
        public int reverseBits(int n) {
            int ans = 0;
            for (int i = 32; i > 0; i--) {
                LogUtil.pring(" ");
                LogUtil.pring("ans:" + Integer.toString(ans, 2));
                LogUtil.pring("n:" + Integer.toString(n, 2));
                LogUtil.pring("n & 1:" + Integer.toString(n & 1, 2));
                ans <<= 1;
                ans += (n & 1);
                n >>= 1;


            }
            return ans;
        }
    }

    /**
     * 若要翻转一个二进制串，可以将其均分成左右两部分，对每部分递归执行翻转操作，然后将左半部分拼在右半部分的后面，即完成了翻转。
     * 由于左右两部分的计算方式是相似的，利用位掩码和位移运算，我们可以自底向上地完成这一分治流程。
     * 对于递归的最底层，我们需要交换所有奇偶位：
     * 取出所有奇数位和偶数位；
     * 将奇数位移到偶数位上，偶数位移到奇数位上。
     * 类似地，对于倒数第二层，每两位分一组，按组号取出所有奇数组和偶数组，然后将奇数组移到偶数组上，偶数组移到奇数组上。以此类推。
     * 需要注意的是，在某些语言（如 Java）中，没有无符号整数类型，因此对 n 的右移操作应使用逻辑右移。
     */
    private static class HardSolution {
        private static final int M1 = 0x55555555; // 01010101010101010101010101010101
        private static final int M2 = 0x33333333; // 00110011001100110011001100110011
        private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
        private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

        public int reverseBits(int n) {
            n = n >>> 1 & M1 | (n & M1) << 1;
            n = n >>> 2 & M2 | (n & M2) << 2;
            n = n >>> 4 & M4 | (n & M4) << 4;
            n = n >>> 8 & M8 | (n & M8) << 8;
            return n >>> 16 | n << 16;
        }
    }

}
