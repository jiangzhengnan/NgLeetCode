package com.ng.code.menu.递归回溯;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 日期:
 * link:
 * https://leetcode.cn/problems/VvJkup/description/
 *
 * 原题描述:
 * 给出一组数字，返回该组数字的所有排列
 * 例如：
 * [1,2,3]的所有排列如下
 * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], [3,2,1].
 * （以数字在数组中的位置靠前为优先级，按字典序排列输出。）
 * 数据范围：数字个数 0 < n \le 60<n≤6
 * 要求：空间复杂度 O(n!)O(n!) ，时间复杂度 O(n!）O(n!）
 *
 * 示例:
 * 示例1
 * 输入：
 * [1,2,3]
 * 返回值：
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例2
 * 输入：
 * [1]
 * 返回值：
 * [[1]]
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅱ_全排列 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 1, 2};
        LogUtil.print(EasySolution.permute(data).toString());
    }

    /**
     * 递归
     * 算法的思路主要就是：选择与撤销
     * 例如：1开头的有，[1,2,3]，接着3撤销，2撤销，然后选择3，再选择2，就有了[1,3,2]
     */
    private static class EasySolution {
        // 存所有排列的集合
        static ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        public static ArrayList<ArrayList<Integer>> permute(int[] num) {
            // 存一种排列
            LinkedList<Integer> list = new LinkedList<>();
            // 递归进行
            backTrack(num, list);
            return res;
        }

        public static void backTrack(int[] num, LinkedList<Integer> list) {
            // 当list中的长度等于数组的长度，则证明此时已经找到一种排列了
            if (list.size() == num.length) {
                // add进返回结果集中
                res.add(new ArrayList<>(list));
                return;
            }
            // 遍历num数组
            for (int i = 0; i < num.length; i++) {
                // 若当前位置中的数已经添加过了则跳过
                if (list.contains(num[i]))
                    continue;
                // 选择该数
                list.add(num[i]);
                // 继续寻找
                backTrack(num, list);
                // 撤销最后一个
                list.removeLast();
            }
        }

    }

    /**
     * 非递归版
     * 这种方法不使用递归，其实也是一个选择和撤销的过程，只是不使用递归来完成。
     * 通过插入的方式，一次性找到所有的情况。
     * 例如：第一次选择1，接着可以在1前面和后面插入2，则变为 1，2 和 2，1；
     * 接着可选择3，3插入到1，2中有三种分别为 3，1，2；1，3，2；1，2，3；然后3插入2，1也有三种。
     * 其实就是找到能插的位置，同一个数可以插在不同的位置，则构成了另外的排列。
     */
    private static class HardSolution {
        // 所有的排列结果集
        static ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        public static ArrayList<ArrayList<Integer>> permute(int[] num) {
            ArrayList<Integer> list = new ArrayList<>();
            // 先对res中加入一个空的list，给第一次插入制造一个空间。
            res.add(list);
            // 整个循环的次数为num的元素个数
            for (int i = 0; i < num.length; i++) {

                ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
                // 遍历此时的排列结果
                for (ArrayList<Integer> r : res) {
                    // 根据集合的大小，使用for循环在可插入的位置进行插入
                    for (int j = 0; j < r.size() + 1; j++) {
                        // 在第j个位置插入
                        r.add(j, num[i]);
                        // 此时构成新的排列集合，可能是不完整的排列集合（例如：[1,2];[2,1]这类）
                        ArrayList<Integer> temp = new ArrayList<>(r);
                        // 放进去tmp临时集合中
                        tmp.add(temp);
                        // 将刚插入的数移除掉，为了将同样的这个插入不同的位置
                        r.remove(j);
                    }
                }
                // 最后赋给res进行返回
                res = new ArrayList<>(tmp);
            }
            return res;
        }

    }

}
