package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/combination-sum-ii/description/
 * 原题描述:
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_组合总和2 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        int[] data = new int[]{1, 2, 2, 5, 5};
        LogUtil.print(easySolution.combinationSum2(data, 8).toString());
    }

    private static class EasySolution {

        //https://leetcode.cn/problems/combination-sum-ii/solutions/14753/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {

            LinkedList<Integer> data = new LinkedList<>();
            Arrays.sort(candidates);
            backTrace(candidates, data, target, 0);
            return result;
        }

        List<List<Integer>> result = new ArrayList();

        public void backTrace(int[] candidates, LinkedList<Integer> data, int target, int start) {
            if (0 == target) {
                result.add(new ArrayList<>(data));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                //超出目标值
                if (candidates[i] > target) {
                    break;
                }
                //i > begin主要是为了确保在同一层中，有数字相同时，只选择第一个数字；
                //比如 1 2 2 5 5
                //只能选择 1 2 5
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                data.add(candidates[i]);

                backTrace(candidates, data, target - candidates[i], i + 1);

                data.removeLast();
            }
        }

    }

    private static class HardSolution {

    }

}
