package com.ng.code.menu.二叉树;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.TreeNode;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/diameter-of-binary-tree/?favorite=2cktkvj
 * 原题描述:
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * 示例:
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
 *
 * [求树的深度]
 */

@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅰ_二叉树的直径 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.createTreeNode(1, 2, 3, 4, 5);
        LogUtil.print(treeNode);
        LogUtil.print(Ⅰ_二叉树的直径.EasySolution.diameterOfBinaryTree(treeNode));
    }

    private static class EasySolution {

        static int max = 0;

        public static int diameterOfBinaryTree(TreeNode root) {
            depth(root);
            return max;
        }

        public static int depth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int Left = depth(node.left);
            int Right = depth(node.right);
            max = Math.max(Left + Right, max);//将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
            return Math.max(Left, Right) + 1;//返回节点深度
        }
    }

    private static class HardSolution {

    }

}
