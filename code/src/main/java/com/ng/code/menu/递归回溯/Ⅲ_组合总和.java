package com.ng.code.menu.递归回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_组合总和 {

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};

        LogUtil.print(EasySolution.combinationSum(candidates, 7).toString());
    }

    private static class EasySolution {

        /**
         * 思路:通过 深度优先遍历 实现，使用一个列表，在 深度优先遍历 变化的过程中，
         * 遍历所有可能的列表并判断当前列表是否符合题目的要求
         * 答案解析：
         * https://leetcode.cn/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
         */
        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            Deque<Integer> path = new ArrayDeque<>();
            dfs(candidates, 0, len, target, path, res);
            return res;
        }

        /**
         * @param candidates 候选数组
         * @param begin      搜索起点
         * @param len        冗余变量，是 candidates 里的属性，可以不传
         * @param target     每减去一个元素，目标值变小
         * @param path       从根结点到叶子结点的路径，是一个栈
         * @param res        结果集列表
         */
        private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
            // target 为负数和 0 的时候不再产生新的孩子结点
            if (target < 0) {
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 重点理解这里从 begin 开始搜索的语意
            for (int i = begin; i < len; i++) {
                path.addLast(candidates[i]);

                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
                dfs(candidates, i, len, target - candidates[i], path, res);

                // 状态重置
                path.removeLast();
            }
        }

    }

    private static class HardSolution {

    }

}
