package com.ng.code.menu.模拟;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置
 * ，即将A中的数据由（A0 A1 ……AN-1 ）变换为（AN-M …… AN-1 A0 A1 ……AN-M-1 ）（最后 M 个数循环移至最前面的 M 个位置）。如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？
 * 数据范围：0 < n \le 1000<n≤100，0 \le m \le 10000≤m≤1000
 * 进阶：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * 示例:
 * 输入：
 * 6,2,[1,2,3,4,5,6]
 * 返回值：
 * [5,6,1,2,3,4]
 * 复制
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_旋转数组 {

    public static void main(String[] args) {

        LogUtil.print(EasySolution.solve(6, 2, new int[]{1, 2, 3, 4, 5, 6}));
    }

    //new一个数组，依次取值
    private static class EasySolution {
        public static int[] solve(int n, int m, int[] a) {
            if (m > n) {
                m = m % n;
            }
            if (m == n || m == 0) {
                return a;
            }
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                if (i >= m) {
                    result[i] = a[i - m];
                } else {
                    result[i] = a[n - (m - i)];
                }
            }

            return result;
        }

    }

    /**
     * 很容易想到三次反转即可满足题目的要求
     * 唯一需要注意的就是m需要取模，因为当移动的次数大于数组的长度时候，其实就相当于向右移动了m%n次。
     */
    private static class HardSolution {
        public int[] solve(int n, int m, int[] a) {

            m = m % n;
            // 0 - n
            reverse(a, 0, n - 1);
            // 0 - m
            reverse(a, 0, m - 1);
            // m - n
            return reverse(a, m, n - 1);


        }

        private int[] reverse(int[] a, int start, int end) {
            while (start < end) {
                int tmp = a[start];
                a[start] = a[end];
                a[end] = tmp;
                start++;
                end--;
            }
            return a;
        }


    }

}
