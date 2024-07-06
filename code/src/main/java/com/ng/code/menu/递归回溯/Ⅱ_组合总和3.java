package com.ng.code.menu.递归回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/combination-sum-iii/description/
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_组合总和3 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
    }

    private static class EasySolution {

        List<List<Integer>> result = new ArrayList();

        public List<List<Integer>> combinationSum3(int k, int n) {

            LinkedList<Integer> data = new LinkedList();
            backTrace(data, k, n, 1);
            return result;
        }

        private void backTrace(LinkedList<Integer> data, int k, int target, int start) {
            if (data.size() == k && target == 0) {
                result.add(new ArrayList(data));
                return;
            }
            for (int i = start; i <= 9; i++) {
                data.add(i);
                backTrace(data, k, target - i, i + 1);
                data.removeLast();

            }
        }

    }

    private static class HardSolution {

    }

}
