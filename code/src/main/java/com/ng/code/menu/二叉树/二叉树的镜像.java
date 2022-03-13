package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/a9d0ecbacef9410ca97463e4a5c83be7?tpId=295&tqId=1374963&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 数据范围：二叉树的节点数 0 \le n \le 10000≤n≤1000 ， 二叉树每个节点的值 0\le val \le 10000≤val≤1000
 * 要求： 空间复杂度 O(n)O(n) 。本题也有原地操作，即空间复杂度 O(1)O(1) 的解法，时间复杂度 O(n)O(n)
 * <p>
 * 输入：
 * {8,6,10,5,7,9,11}
 * 返回值：
 * {8,10,6,11,9,7,5}
 * 说明：
 * 如题面所示
 */
@Solution(easy = 0, hard = 0)
public class 二叉树的镜像 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(8, 6, 10, 5, 7, 9, 11);
        EasySolution.Mirror(data).print();
    }

    /**
     * 递归交换左右节点
     */
    private static class EasySolution {

        public static TreeNode Mirror(TreeNode pRoot) {
            // write code here
            if (pRoot == null) {
                return null;
            }
            TreeNode left = pRoot.left;
            TreeNode right = pRoot.right;
            pRoot.right = left;
            pRoot.left = right;
            Mirror(pRoot.left);
            Mirror(pRoot.right);
            return pRoot;
        }

    }

    /**
     *
     */
    private static class HardSolution {

    }

}
