package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/subsets/
 * 原题描述:
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例:
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_子集 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.subsets(new int[]{1, 2, 3}).toString());

    }

    private static class EasySolution {

        static List<List<Integer>> ans = new ArrayList<>();
        static List<Integer> temp = new LinkedList<>();

        public static List<List<Integer>> subsets(int[] nums) {
            dfs(nums, 0);
            return ans;
        }
        //nums:数组， startIndex:截取数组的起始值
        private static void dfs(int[] nums, int startIndex) {
            //注意一开始要添加空集合
            ans.add(new ArrayList<>(temp));
            //终止条件
            if (startIndex == nums.length) return;
            for (int i = startIndex; i < nums.length; i++) {
                temp.add(nums[i]);
                dfs(nums, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private static class HardSolution {

    }

}
