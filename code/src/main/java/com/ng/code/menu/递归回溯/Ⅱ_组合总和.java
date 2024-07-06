package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/combination-sum/?favorite=2cktkvj
 * 原题描述:
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * <p>
 * 示例:
 * 示例1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_组合总和 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        int[] candidates = new int[]{2, 3, 6, 7};
        LogUtil.print(easySolution.combinationSum(candidates, 7).toString());
    }

    private static class EasySolution {

        List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            LinkedList<Integer> data = new LinkedList<>();
            Arrays.sort(candidates);
            backTrace(candidates, data, target, 0);
            return result;
        }

        public void backTrace(int[] candidates, LinkedList<Integer> data, int target, int start) {
            if (target == 0) {
                result.add(new ArrayList<>(data));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                //超出目标值
                if (candidates[i] > target) {
                    break;
                }
                data.add(candidates[i]);
                backTrace(candidates, data, target - candidates[i], i);
                data.removeLast();
            }
        }
    }

    private static class HardSolution {

    }

}
