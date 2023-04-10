package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=295&tqId=23253&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示
 *
 * 数据范围：输入二叉树的节点数 0 \le n \le 10000≤n≤1000，二叉树中每个节点的值 0\le val \le 10000≤val≤1000
 * 要求：空间复杂度O(1)O(1)（即在原树上操作），时间复杂度 O(n)O(n)
 *
 * 注意:
 * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
 * 2.返回链表中的第一个节点的指针
 * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
 * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出
 *
 * 输入描述：
 * 二叉树的根节点
 * 返回值描述：
 * 双向链表的其中一个头节点。
 * 示例1
 * 输入：
 * {10,6,14,4,8,12,16}
 * 返回值：
 * From left to right are:4,6,8,10,12,14,16;From right to left are:16,14,12,10,8,6,4;
 * 说明：
 * 输入题面图中二叉树，输出的时候将双向链表的头节点返回即可。
 */
@Solution(easy = 1, hard = 1, partice = 0)
public class Ⅱ_二叉搜索树与双向链表 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(10, 6, 14, 4, 8, 12, 16);
        HardSolution.Convert(data);
        //LogUtil.pring(data);
    }

    /**
     * 中序遍历存起来，然后再依次连接每一位
     * 但是这样相当于创建了新的节点
     */
    private static class EasySolution {
        static List<TreeNode> treeList;

        public static TreeNode Convert(TreeNode pRootOfTree) {
            if (pRootOfTree == null) {
                return pRootOfTree;
            }
            treeList = new ArrayList<>();
            midQuery(pRootOfTree);
            for (int i = 0; i < treeList.size() - 1; i++) {
                treeList.get(i).right = treeList.get(i + 1);
                treeList.get(i + 1).left = treeList.get(i);
            }
            return treeList.get(0);
        }

        //中序遍历
        private static void midQuery(TreeNode pRootOfTree) {
            if (pRootOfTree == null) {
                return;
            }
            midQuery(pRootOfTree.left);
            treeList.add(pRootOfTree);
            midQuery(pRootOfTree.right);
        }

    }

    /**
     * 1.使用一个节点 preNode 指向前继
     * 原     10,6,14,4,8,12,16
     * 中序后  4,6,8,10,12,14,16
     * 4就是6的前继
     * 2.对于当前节点， left指向preNode， 然后preNode的right指向后继，这样就变成一个双向链表了
     */
    private static class HardSolution {

        static TreeNode pre = null;
        static TreeNode root = null;

        public static TreeNode Convert(TreeNode pRootOfTree) {
            if (pRootOfTree == null) return null;
            Convert(pRootOfTree.left);

            if (root == null) {
                root = pRootOfTree;
            }
            if (pre != null) {
                pRootOfTree.left = pre;
                pre.right = pRootOfTree;
            }
            pre = pRootOfTree;

            Convert(pRootOfTree.right);
            return root;
        }
    }

}
