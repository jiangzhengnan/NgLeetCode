package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link: https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence-ii/description/
 * 原题描述:
 * 会员题，字节题
 * 题目描述：
 * 给定一个二叉树，你需要找出二叉树中最长的连续序列路径的长度。
 * 请注意，该路径可以是递增的或者是递减。例如，[1,2,3,4] 和 [4,3,2,1] 都被认为是合法的，而路径 [1,2,4,3] 则不合法。另一方面，路径可以是 子-父-子
 * 顺序，并不一定是 父-子 顺序。
 * 示例 1:
 * 输入:
 * 输出: 2
 * 解释: 最长的连续路径是 [1, 2] 或者 [2, 1]。
 * 示例 2:
 * 输入:
 * 输出: 3
 * 解释: 最长的连续路径是 [1, 2, 3] 或者 [3, 2, 1]。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_二叉树中最长的连续序列 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        /*
         *    3
         *  2   1
         */
        LogUtil.print(easySolution.longestConsecutive(TreeNode.createTreeNode(3, 2, 2)));

    }

    private static class EasySolution {
        int maxVal = 0;

        public int longestConsecutive(TreeNode root) {
            longestPath(root);
            return maxVal;
        }

        public int[] longestPath(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }

            int inr = 1, dcr = 1;

            // Check left subtree
            if (root.left != null) {
                int[] l = longestPath(root.left);
                if (root.val == root.left.val + 1) {
                    dcr = l[1] + 1;
                } else if (root.val == root.left.val - 1) {
                    inr = l[0] + 1;
                }
            }

            // Check right subtree
            if (root.right != null) {
                int[] r = longestPath(root.right);
                if (root.val == root.right.val + 1) {
                    dcr = Math.max(dcr, r[1] + 1);
                } else if (root.val == root.right.val - 1) {
                    inr = Math.max(inr, r[0] + 1);
                }
            }

            // Update the maximum value and return the current inr and dcr
            maxVal = Math.max(maxVal, dcr + inr - 1);
            return new int[]{inr, dcr};
        }
    }

    private static class HardSolution {

    }

}
