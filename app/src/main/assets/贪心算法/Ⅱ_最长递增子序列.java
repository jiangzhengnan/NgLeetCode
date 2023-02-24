package com.ng.code.menu.贪心算法;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/longest-increasing-subsequence/
 * 原题描述:
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_最长递增子序列 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        int[] arrays = new int[]{10, 9, 2, 5, 3, 7, 101, 18};



        LogUtil.pring(easySolution.lengthOfLIS(arrays));
    }

    /**
     * 定义  dp[i] 为考虑前 i  个元素，以第 i  个数字结尾的最长上升子序列的长度，注意  nums[i] 必须被选取。
     * <p>
     * 我们从小到大计算  dp 数组的值，在计算  [i]dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，则状态转移方程为：
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 nums[i]。
     * 由于 dp[j] 代表 nums[0…j] 中以nums[j] 结尾的最长上升子序列，
     * 所以如果能从 dp[j] 这个状态转移过来，那么nums[i] 必然要大于nums[j]，才能将nums[i]
     * 放在nums[j] 后面以形成更长的上升子序列。
     */
    private static class EasySolution {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxans = 1;
            for (int i = 1; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }
    }

    private static class HardSolution {

    }

}
