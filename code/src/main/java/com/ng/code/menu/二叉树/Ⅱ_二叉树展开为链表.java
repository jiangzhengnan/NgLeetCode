package com.ng.code.menu.二叉树;

import com.ng.base.utils.TreeNode;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType
 * =study-plan-v2&envId=top-interview-150
 * 原题描述:
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 示例:
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_二叉树展开为链表 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

    }

    private static class HardSolution {

        //递归返回链表中的最后一位
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            subflatten(root);
        }

        public TreeNode subflatten(TreeNode root) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode last = root; //左右为空时返回root
            root.left = null;
            if (left != null) {
                //如果左子节点不为空，则root 右结点-> left
                root.right = left;

                //last 为左子结点链表最后一位
                last = subflatten(left);
            }
            if (right != null) {

                //如果右子结点不为空，则 root -> left last -> right
                last.right = right;

                // last 为右子结点最后一位
                last = subflatten(right);
            }
            return last;
        }
    }

}
