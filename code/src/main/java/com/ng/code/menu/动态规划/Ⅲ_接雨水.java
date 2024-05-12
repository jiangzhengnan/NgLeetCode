package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-100-liked
 * 原题描述:
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_接雨水 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        /**
         * https://leetcode.cn/problems/trapping-rain-water/solutions/9112/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/?envType=study-plan-v2&envId=top-100-liked
         */
        public int trap(int[] height) {
            int n = height.length;
            if (n == 0) {
                return 0;
            }

            int[] leftMax = new int[n];
            leftMax[0] = height[0];
            for (int i = 1; i < n; ++i) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }

            int[] rightMax = new int[n];
            rightMax[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; --i) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }

            int ans = 0;
            for (int i = 0; i < n; ++i) {
                ans += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
            return ans;
        }
    }

    private static class HardSolution {
        /**
         * 双指针解法
         * 注意到动态规划中
         * 对于位置 i 的接水量取决于 leftMax 和 rightMax 中的较小者
         * 所以我们不必真的知道较大者是谁
         * 只要知道较小者是谁就可以了
         * 初始化 left = 0, right = n-1, leftMax = 0, rightMax = 0
         * 注意到对于位置 left 来说, leftMax 是真正意义上的左侧最大值, 而 rightMax 不是真的右侧最大值
         * 而对于位置 right 来说, rightMax 是真正意义上的右侧最大值, 而 leftMax 不是真的左侧最大值
         * 从左往右扫描
         * 1. 使用 height[left] 和 height[right] 更新 leftMax, rightMax
         * 2. 若 height[left] < height[right], 则说明对于位置 left 来说,
         * leftMax 一定小于其右侧真正意义上的最大值
         * 因为连当前右侧的局部最大值 rightMax 都比不过, 更比不过右侧真正意义上的最大值
         * 而我们又不需要真的知道右侧真正意义上的最大值
         * 3. 类似地处理 height[left] >= height[right] 的情况
         */
        public int trap(int[] height) {
            int ans = 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                if (height[left] < height[right]) {
                    ans += leftMax - height[left];
                    ++left;
                } else {
                    ans += rightMax - height[right];
                    --right;
                }
            }
            return ans;
        }

    }

}
