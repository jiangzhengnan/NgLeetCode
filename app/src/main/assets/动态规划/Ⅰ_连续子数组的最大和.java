package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 * 要求:时间复杂度为 O(n)O(n)，空间复杂度为 O(n)O(n)
 * 进阶:时间复杂度为 O(n)O(n)，空间复杂度为 O(1)O(1)
 *
 * 示例:
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 返回值：
 * 18
 *
 * 说明：
 * 经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18
 * 示例2
 * 输入：
 * [2]
 * 返回值：
 * 2
 *
 * 示例3
 * 输入：
 * [-10]
 * 返回值：
 * -10
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅰ_连续子数组的最大和 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));
    }

    //动态规划
    private static class EasySolution {

        public static int FindGreatestSumOfSubArray(int[] array) {
            int result = -101;
            int now = 0;
            for (int temp : array) {
                now = Math.max(temp, now + temp);

                result = Math.max(now, result);
            }

            return result;
        }
    }

    private static class HardSolution {

    }

}
