package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * link:
 * https://leetcode.cn/problems/permutations-ii/description/
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅱ_全排列2 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        int[] data = new int[]{1, 1, 2};
        LogUtil.print(easySolution.permuteUnique(data).toString());
    }

    private static class EasySolution {

        List<List<Integer>> result = new ArrayList<>();
        boolean[] vist;

        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums == null || nums.length == 0) {
                return result;
            }
            Arrays.sort(nums);
            vist = new boolean[nums.length];
            LinkedList<Integer> data = new LinkedList<>();
            backTrace(nums, data);
            return result;
        }

        public void backTrace(int[] nums, LinkedList<Integer> data) {
            if (data.size() == nums.length) {
                result.add(new ArrayList<>(data));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (vist[i] || (i > 0 && nums[i] == nums[i - 1] && !vist[i - 1])) {
                    continue;
                }
                data.add(nums[i]);
                vist[i] = true;
                backTrace(nums, data);
                data.removeLast();
                vist[i] = false;
            }
        }
    }

}
