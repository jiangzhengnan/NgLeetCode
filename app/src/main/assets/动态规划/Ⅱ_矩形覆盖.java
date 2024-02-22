package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=23283&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，
 * 从同一个方向看总共有多少种不同的方法？
 * 数据范围：0≤n≤38
 * 进阶：空间复杂度 O(1)\O(1)  ，时间复杂度 O(n)\O(n)
 * 注意：约定 n == 0 时，输出 0
 * <p>
 * 示例:
 * 输入：
 * 0
 * 返回值：
 * 0
 * <p>
 * 输入：
 * 4
 * 返回值：
 * 5
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_矩形覆盖 {

    public static void main(String[] args) {

    }

    /**
     * 规律递归
     * n    way
     * 0    0
     * 1    1
     * 2    2
     * 3    3
     * 4    5
     * f(n) = f(n-1) + f(n-2)
     */
    private static class EasySolution {

        public int rectCover(int n) {
            if (n <= 2) {
                return n;
            }
            return rectCover(n - 1) + rectCover(n - 2);
        }

    }

    /**
     * 记忆化递归
     */
    private static class HardSolution {
        static int[] cache = new int[1000];

        public int rectCover(int n) {
            if (cache[n] != 0) {
                return cache[n];
            }
            if (n <= 2) {
                cache[n] = n;
            } else {
                cache[n] = rectCover(n - 1) + rectCover(n - 2);
            }

            return cache[n];

        }

    }

}
