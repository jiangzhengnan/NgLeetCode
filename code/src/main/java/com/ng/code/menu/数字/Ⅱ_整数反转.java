package com.ng.code.menu.数字;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * https://leetcode.cn/problems/reverse-integer/
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_整数反转 {

    public static void main(String[] args) {

    }

    private static class EasySolution {
        public static int reverse(int x) {
            int res = 0;
            int last = 0;
            while (x != 0) {
                int tmp = x % 10;
                last = res;

                res = res * 10 + tmp;
                if (last != res / 10) {
                    return 0;
                }

                x /= 10;

            }
            return res;
        }
    }

    private static class HardSolution {

    }

}
