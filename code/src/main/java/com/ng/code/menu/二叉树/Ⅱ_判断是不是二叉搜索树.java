package com.ng.code.menu.二叉树;

import java.util.Stack;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * <p>
 * 原题描述:
 * 给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
 * 二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点且右子树上的所有节点均大于当前节点。
 * 示例1
 * 输入：
 * {1,2,3}
 * 返回值：
 * false
 * 示例2
 * 输入：
 * {2,1,3}
 * 返回值：
 * true
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_判断是不是二叉搜索树 {

    public static void main(String[] args) {
        TreeNode data1 = TreeNode.createTreeNode(1, 2, 3);
        TreeNode data2 = TreeNode.createTreeNode(5,1,4,-1,-1,3,6);

        LogUtil.pring(EasySolution.isValidBST(data1));
        LogUtil.pring(HardSolution.isValidBST(data2));

    }

    /**
     * 递归
     */
    private static class EasySolution {

        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        public static boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null) {
                return true;
            }
            //		 首先，当前节点的值不能小于最小值，不能大于最大值（左子节点无最小值限制，最大值为当前节点值；右节点无最大值，限制最小值为当前节点值）
            if (min != null && root.val <= min.val) {
                return false;
            }
            if (max != null && root.val >= max.val) {
                return false;
            }
            //		 由上向下递归，将当前节点值作为，左节点的最大限制值，右节点的最小限制值
            return isValidBST(root.left, min, root)
                   && isValidBST(root.right, root, max);

        }
    }

    /**
     * 中序遍历检查
     */
    private static class HardSolution {

        public static boolean isValidBST(TreeNode root) {
            Stack<TreeNode> data = new Stack<>();
            TreeNode p = root;
            int pre = Integer.MIN_VALUE;

            while (!data.isEmpty() || p != null) {
                while (p != null) {
                    data.push(p);
                    //遍历左子树
                    p = p.left;
                }
                p = data.pop();
                //判断 中序序列中前一个值大于后一个值，则返回false
                if (pre > p.val) {
                    return false;
                }
                pre = p.val;
                //遍历右子树
                p = p.right;
            }
            return true;
        }

    }

}
