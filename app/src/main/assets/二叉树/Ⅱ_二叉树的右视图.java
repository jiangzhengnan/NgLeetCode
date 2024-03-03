package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/binary-tree-right-side-view/description/
 * 原题描述:
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * <p>
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 * <p>
 * 示例 3:
 * 输入: []
 * 输出: []
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_二叉树的右视图 {

    public static void main(String[] args) {
        LogUtil.print(
            EasySolution.rightSideView(
                TreeNode.createTreeNode(1, 2, 3, -1, 5, -1, 4)).toString());
    }

    /**
     * 层序遍历，最好的方式。(hard = easy)
     */
    private static class EasySolution {

        public static List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            //层序遍历
            if (root == null) {
                return result;
            }
            LinkedList<TreeNode> treeNodes = new LinkedList<>();
            treeNodes.add(root);
            while (treeNodes.size() > 0) {
                int size = treeNodes.size();
                result.add(treeNodes.get(treeNodes.size() - 1).val);

                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = treeNodes.pop();
                    if (tempNode.left != null) {
                        treeNodes.add(tempNode.left);
                    }
                    if (tempNode.right != null) {
                        treeNodes.add(tempNode.right);
                    }
                }
            }

            return result;
        }



    }

    private static class HardSolution {
    }

}
