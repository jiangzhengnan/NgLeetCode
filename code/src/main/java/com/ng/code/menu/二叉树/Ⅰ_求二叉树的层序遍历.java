package com.ng.code.menu.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.TreeNode;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 描述:
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
@Solution(easy = 1, hard = 0, partice = 1)
public class Ⅰ_求二叉树的层序遍历 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(3, 9, 20, -1, -1, 15, 7);
        LogUtil.pring(levelOrder(data).toString());
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        int size = 0;
        List<Integer> tempRes = new ArrayList<>();
        while (list.size() > 0) {
            size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = list.pop();
                if (temp.left != null) {
                    list.add(temp.left);
                }
                if (temp.right != null) {
                    list.add(temp.right);
                }
                tempRes.add(temp.val);
            }
            res.add(new ArrayList<>(tempRes));
            tempRes.clear();
        }
        return res;
    }
}
