package com.ng.code.menu.哈希;

import java.util.HashSet;
import java.util.Set;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/longest-consecutive-sequence/
 * 原题描述:
 * 字节跳动二面原题
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例:
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_最长连续序列 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        int[] array1 = new int[]{100, 4, 200, 1, 3, 2};
        int[] array2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LogUtil.pring(easySolution.longestConsecutive(array1));
        LogUtil.pring(easySolution.longestConsecutive(array2));
    }

    private static class EasySolution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> num_set = new HashSet<Integer>();
            for (int num : nums) {
                num_set.add(num);
            }

            int longestStreak = 0;

            for (int num : num_set) {
                if (!num_set.contains(num - 1)) {
                    int currentStreak = 1;

                    while (num_set.contains(num + 1)) {
                        num += 1;
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }
    }

    private static class HardSolution {

    }

}
