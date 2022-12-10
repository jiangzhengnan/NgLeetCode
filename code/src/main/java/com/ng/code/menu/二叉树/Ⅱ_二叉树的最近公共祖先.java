package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/?favorite=2ckc81c
 * 原题描述:
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例:
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_二叉树的最近公共祖先 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            // 如果p,q为根节点，则公共祖先为根节点
            if (root.val == p.val || root.val == q.val) return root;
            // 如果p,q在左子树，则公共祖先在左子树查找
            if (find(root.left, p) && find(root.left, q)) {
                return lowestCommonAncestor(root.left, p, q);
            }
            // 如果p,q在右子树，则公共祖先在右子树查找
            if (find(root.right, p) && find(root.right, q)) {
                return lowestCommonAncestor(root.right, p, q);
            }
            // 如果p,q分属两侧，则公共祖先为根节点
            return root;
        }

        private boolean find(TreeNode root, TreeNode c) {
            if (root == null) return false;
            if (root.val == c.val) {
                return true;
            }

            return find(root.left, c) || find(root.right, c);
        }

    }

    private static class HardSolution {

    }

}
