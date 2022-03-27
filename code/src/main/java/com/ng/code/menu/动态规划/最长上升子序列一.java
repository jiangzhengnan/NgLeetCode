package com.ng.code.menu.动态规划;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 描述
 * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
 * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
 * 我们定义一个序列是 严格上升 的，当且仅当该序列不存在两个下标 ii 和 jj 满足 i<ji<j 且 arr_i \geq arr_jarr
 * 数据范围： 0\leq n \leq 10000≤n≤1000
 * 要求：时间复杂度 O(n^2)  空间复杂度 O(n)
 *
 * 示例:
 * 输入：
 * [6,3,1,5,2,3,7]
 * 返回值：
 * 4
 * 说明：
 * 该数组最长上升子序列为 [1,2,3,7] ，长度为4
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 最长上升子序列一 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.LIS(new int[]{6, 3, 1, 5, 2, 3, 7}));
    }

    /**
     * 动态规划
     */
    private static class EasySolution {

        public static int LIS(int[] arr) {
            int n = arr.length;
            //特殊请款判断
            if (n == 0) return 0;
            //dp[i]表示以下标i结尾的最长上升子序列长度
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                //初始化为1
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[i] > arr[j]) {
                        //只要前面某个数小于当前数，则要么长度在之前基础上加1，要么保持不变，取较大者
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int maxResult = Integer.MIN_VALUE;
            for (int temp : dp) {
                if (temp > maxResult) {
                    maxResult = temp;
                }
            }
            //返回所有可能中的最大值
            return maxResult;
        }

    }

    private static class HardSolution {

    }

}
