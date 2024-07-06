package com.ng.code.menu.二叉树;

import com.ng.base.utils.TreeNode;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/count-complete-tree-nodes/description/?envType=study-plan-v2
 * &envId=top-interview-150
 * 原题描述:
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~
 * 2h 个节点。
 * 示例:
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * 示例 2：
 * 输入：root = []
 * 输出：0
 * 示例 3：
 * 输入：root = [1]
 * 输出：1
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_完全二叉树的结点个数 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

    }

    private static class HardSolution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = countLevel(root.left);
            int right = countLevel(root.right);
            if (left == right) {
                //左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。再对右子树进行递归统计。
                return countNodes(root.right) + ((int) Math.pow(2, left) - 1) + 1;
            } else {
                return countNodes(root.left) + ((int) Math.pow(2, right) - 1) + 1;
            }
        }

        private int countLevel(TreeNode root) {
            int level = 0;
            while (root != null) {
                level++;
                root = root.left;
            }
            return level;
        }
    }

}
