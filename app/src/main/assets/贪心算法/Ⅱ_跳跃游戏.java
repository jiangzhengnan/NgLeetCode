package com.ng.code.menu.贪心算法;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/jump-game/
 * 原题描述:
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 示例:
 * 示例1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_跳跃游戏 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    /**
     * 自己的解法
     * 用一个数组表示某一位是否能从第一位到达
     */
    private static class EasySolution {
        public boolean canJump(int[] nums) {
            //某一位是否能从第一位到达
            boolean[] dp = new boolean[nums.length];
            dp[0] = true;
            for (int i = 1; i < nums.length; i++) {

                boolean canJump = false;
                for (int j = 0; j < i; j++) {
                    if (dp[j] && (j >= i - nums[j])) {
                        canJump = true;
                        break;
                    }
                }
                if (canJump) {
                    dp[i] = true;
                }

            }


            return dp[nums.length - 1];
        }
    }

    /**
     * 解题思路：
     * 1.如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     * 2.可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     * 3.如果可以一直跳到最后，就成功了
     */
    private static class HardSolution {
        public static boolean canJump(int[] nums) {
            if (nums == null) {
                return false;
            }
            //前n-1个元素能够跳到的最远距离
            int k = 0;
            for (int i = 0; i <= k; i++) {
                //第i个元素能够跳到的最远距离
                int temp = i + nums[i];
                //更新最远距离
                k = Math.max(k, temp);
                //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
                if (k >= nums.length - 1) {
                    return true;
                }
            }
            //最远距离k不再改变,且没有到末尾元素
            return false;
        }
    }

}
