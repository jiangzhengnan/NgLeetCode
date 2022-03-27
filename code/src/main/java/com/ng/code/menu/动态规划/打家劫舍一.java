package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * https://www.nowcoder.com/practice/c5fbf7325fbd4c0ea3d0c3ea6bc6cc79?tpId=295&tqId=2285793&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，
 * 即，如果偷了第一家，就不能再偷第二家；如果偷了第二家，那么就不能偷第一家和第三家。
 * 给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
 *
 * 示例:
 * 示例1
 * 输入：
 * [1,2,3,4]
 * 返回值：
 * 6
 *
 * 说明：
 * 最优方案是偷第 2，4 个房间
 * 示例2
 * 输入：
 * [1,3,6]
 * 返回值：
 * 7
 *
 * 说明：
 * 最优方案是偷第 1，3个房间
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 打家劫舍一 {

    public static void main(String[] args) {

    }

    private static class EasySolution {

        public int rob(int[] nums) {
            // 只有一个元素，就返回该元素的值
            if (nums.length == 1) {
                return nums[0];
            }
            // 两个元素，就返回两元素中其中一个最大值
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            // 定义一个数组，记录 n-1个值的和 和n-2个值的和
            int[] maxVal = new int[nums.length];
            // 初始化结果数组 第0 个元素和第 1个元素
            maxVal[0] = nums[0];
            maxVal[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                maxVal[i] = Math.max(maxVal[i - 1], maxVal[i - 2] + nums[i]);
            }
            return maxVal[maxVal.length - 1];
        }

    }

    private static class HardSolution {

    }

}
