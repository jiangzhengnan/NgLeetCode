package com.ng.code.menu.二叉树;

import java.util.ArrayDeque;
import java.util.Deque;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.TreeNode;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?favorite=ex0k24j
 * 原题描述:
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 示例:
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_二叉搜索树中第K小的元素 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.createTreeNode(3,1,4,-1,2);
        LogUtil.print(treeNode);
        LogUtil.print(EasySolution.kthSmallest(treeNode, 3));
    }

    private static class EasySolution {

        /**
         * 二叉搜索树具有如下性质：
         * 结点的左子树只包含小于当前结点的数。
         * 结点的右子树只包含大于当前结点的数。
         * 所有左子树和右子树自身必须也是二叉搜索树。
         * 二叉树的中序遍历即按照访问左子树——根结点——右子树的方式遍历二叉树；在访问其左子树和右子树时，我们也按照同样的方式遍历；
         * 直到遍历完整棵树。
         *
         * 思路和算法
         * 因为二叉搜索树和中序遍历的性质，所以二叉搜索树的中序遍历是按照键增加的顺序进行的。
         * 于是，我们可以通过中序遍历找到第 kk 个最小元素。
         * 「二叉树的中序遍历」可以参考「94. 二叉树的中序遍历的官方题解」，具体地，
         * 我们使用迭代方法，这样可以在找到答案后停止，不需要遍历整棵树。
         */
        public static int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                --k;
                if (k == 0) {
                    break;
                }
                root = root.right;
            }
            return root.val;
        }

    }

    private static class HardSolution {

    }

}
