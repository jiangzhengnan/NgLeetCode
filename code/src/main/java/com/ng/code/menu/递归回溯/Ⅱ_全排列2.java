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
                /*
                 * 之所以能去重，原因如下，
                 * 以 [1, 1, 2] 作为入参为例，当顶层的 for 循环的第一遍（即 i = 0，取第一个 1 放第一位）执行完成后，
                 * vis[] 数组的值在回溯过程中都会被置为 false，同时会产生一个 [1, 1, 2] 的排列；
                 * 当顶层的 for 循环的第二遍（即 i = 1，取第二个 1 放第一位）执行时，
                 * 上述 if 判断就为 false 了，即不能取第二个 1 放第一位，这会与第一遍重复。
                 *
                 * 可以这样理解：每次只能加入第一个重复数，去保证有序的遍历，重复数有多个，
                 * 一旦之前的那个加入后，下一个就重新变为第一个，那么就可以再加入它。
                 */
                data.add(nums[i]);
                vist[i] = true;
                backTrace(nums, data);
                data.removeLast();
                vist[i] = false;
            }
        }
    }

}
