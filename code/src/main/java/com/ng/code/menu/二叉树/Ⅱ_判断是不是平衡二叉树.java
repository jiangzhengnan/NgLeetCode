package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=295&tqId=23250&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 输入一棵节点数为 n 二叉树，判断该二叉树是否是平衡二叉树。
 * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
 * 平衡二叉树（Balanced Binary Tree），具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 * 示例1
 * 输入：
 * {1,2,3,4,5,6,7}
 * 返回值：
 * true
 * 示例2
 * 输入：
 * {}
 * 返回值：
 * true
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_判断是不是平衡二叉树 {

    public static void main(String[] args) {
        TreeNode data1 = TreeNode.createTreeNode(1, 2, 3, 4, 5, 6, 7);
        LogUtil.pring(EasySolution.IsBalanced_Solution(data1));
    }

    /**
     * dfs
     * 如果一个节点的左右子节点都是平衡的，并且左右子节点的深度差不超过 1，则可以确定这个节点就是一颗平衡二叉树。
     */
    private static class EasySolution {

        public static boolean IsBalanced_Solution(TreeNode root) {
            if (root == null) {
                return true;
            }
            return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right) &&
                    Math.abs(deep(root.left) - deep(root.right)) < 2;
        }

        private static int deep(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(deep(root.left) + 1, deep(root.right) + 1);
        }
    }

    /**
     * 回溯
     * 对于父节点，需要确定两个子节点深度之差小于一。对于作为子节点的立场，需要向自己的上一级节点传递的自己深度
     */
    private static class HardSolution {

        public static boolean IsBalanced_Solution(TreeNode root) {
            if (deep(root) == -1) return false;
            return true;
        }

        public static int deep(TreeNode node) {
            if (node == null) return 0;
            int left = deep(node.left);
            if (left == -1) return -1;
            int right = deep(node.right);
            if (right == -1) return -1;

            //两个子节点深度之差小于一
            if ((left - right) > 1 || (right - left) > 1) {
                return -1;
            }
            //父节点需要向自己的父节点报告自己的深度
            return (left > right ? left : right) + 1;
        }

    }

}
