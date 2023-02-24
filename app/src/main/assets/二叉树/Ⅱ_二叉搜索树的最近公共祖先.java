package com.ng.code.menu.二叉树;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.TreeNode;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/d9820119321945f588ed6a26f0a6991f?tpId=295&tqId=2290592&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 1.对于该题的最近的公共祖先定义:对于有根树T的两个节点p、q，最近公共祖先LCA(T,p,q)表示一个节点x，满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先.
 * 2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值
 * 3.所有节点的值都是唯一的。
 * 4.p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 数据范围:
 * 3<=节点总数<=10000
 * 0<=节点值<=10000
 *
 * 示例1
 * 输入：
 * {7,1,12,0,4,11,14,#,#,3,5},1,12
 * 返回值：
 * 7
 * 说明：
 * 节点1 和 节点12的最近公共祖先是7
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_二叉搜索树的最近公共祖先 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(7, 1, 12, 0, 4, 11, 14, -1, -1, 3, 5);
        data.print();
        LogUtil.pring(EasySolution.lowestCommonAncestor(data, 0, 3));

    }

    /**
     * 利用二叉搜索树的性质
     */
    private static class EasySolution {

        public static TreeNode commonAncestor(TreeNode root, int p, int q) {
            if (null == root) return null;
            if (root.val == p || root.val == q) return root;
            // 通过递归假设我们知道了运算结果 题目含义是不会出现重复节点
            if (p < root.val && q < root.val) return commonAncestor(root.left, p, q);
            else if (p > root.val && q > root.val) return commonAncestor(root.right, p, q);
            else return root;
        }

        public static int lowestCommonAncestor(TreeNode root, int p, int q) {
            // write code here
            return commonAncestor(root, p, q).val;
        }
    }

    /**
     * 无
     */
    private static class HardSolution {
        public static int lowestCommonAncestor(TreeNode root, int p, int q) {
            return query(root, p, q);
        }

        private static int query(TreeNode node, int p, int q) {
            if (node == null) {
                return -1;
            }
            if (node.val == p) {
                return p;
            }
            if (node.val == q) {
                return q;
            }
            if (node.val < p && node.val < q) {
                return query(node.right, p, q);
            } else if (node.val > p && node.val > q) {
                return query(node.left, p, q);
            } else {
                return node.val;
            }
        }
    }

}
