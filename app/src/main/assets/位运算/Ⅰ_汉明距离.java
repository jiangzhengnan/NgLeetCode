package com.ng.code.menu.位运算;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/hamming-distance/
 * 原题描述:
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 示例 2：
 * <p>
 * 输入：x = 3, y = 1
 * 输出：1
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_汉明距离 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.hammingDistance(1, 4));
    }

    /**
     * 移位实现位计数
     * 具体地，记 s = x \oplus ys=x⊕y，我们可以不断地检查 ss 的最低位，如果最低位为 11，
     * 那么令计数器加一，然后我们令 ss 整体右移一位，这样 ss 的最低位将被舍去，原本的次低位就变成了新的最低位。
     * 我们重复这个过程直到 s=0s=0 为止。这样计数器中就累计了 ss 的二进制表示中 11 的数量。
     * <p>
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     * ------------- 异或^之后
     * 5    0 1 0 1
     * 1    0 0 0 1
     * ------------- & 1之后
     * 1    0 0 0 1
     */
    private static class EasySolution {

        /**
         * 算术右移 >> ：舍弃最低位，高位用符号位填补；
         * 逻辑右移 >>> ：舍弃最低位，高位用 0 填补。
         * 如果x和y可能小于0，就应该用逻辑右移，如果用算数右移，负数的最高为1，就会无限循环
         */
        public static int hammingDistance(int x, int y) {
            int s = x ^ y, ret = 0;
            while (s != 0) {
                ret += s & 1;
                s >>= 1;
            }
            return ret;
        }

    }

    private static class HardSolution {

    }

}
