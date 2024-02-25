package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/
 * <p>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_连续子数组的最大和 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    //动态规划
    private static class EasySolution {

        public static int maxSubArray(int[] nums) {
            int max = -10001;
            int nowMax = -10001;
            for (int temp : nums) {
                nowMax = Math.max(temp, temp + nowMax);
                max = Math.max(nowMax, max);
            }

            return max;
        }
    }

    private static class HardSolution {

    }

}
