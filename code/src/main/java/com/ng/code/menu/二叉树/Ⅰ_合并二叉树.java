package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.base.TreeNode;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/7298353c24cc42e3bd5f0e0bd3d1d759?tpId=295&tqId=1025038&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。例如：
 * 输入：
 * {1,3,2,5},{2,1,3,#,4,#,7}
 * 返回值：
 * {3,4,5,5,4,#,7}
 * 说明：
 * 如题面图
 */
@Solution(easy = 1, hard = 1)
public class Ⅰ_合并二叉树 {

    public static void main(String[] args) {
        TreeNode t1 = TreeNode.createTreeNode(1, 3, 2, 5);
        TreeNode t2 = TreeNode.createTreeNode(2, 1, 3, -1, 4, -1, 7);

        EasySolution.mergeTrees(t1, t2).print();

    }

    private static class EasySolution {

        public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            t1.val = t1.val + t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }

    }

    /**
     * 同上
     */
    private static class HardSolution {

    }

}
