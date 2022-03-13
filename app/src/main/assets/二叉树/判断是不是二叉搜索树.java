package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/a69242b39baf45dea217815c7dedb52b?tpId=295&tqId=2288088&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
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
@Solution(easy = 0, hard = 0)
public class 判断是不是二叉搜索树 {

    public static void main(String[] args) {
        TreeNode data1 = TreeNode.createTreeNode(1, 2, 3);
        TreeNode data2 = TreeNode.createTreeNode(2, 1, 3);

        LogUtil.pring(HardSolution.isValidBST(data1));
        LogUtil.pring(HardSolution.isValidBST(data2));

    }

    /**
     * 递归
     */
    private static class EasySolution {

        public static boolean isValidBST(TreeNode root) {
            return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private static boolean dfs(TreeNode root, int minValue, int maxValue) {
            if (root == null) {
                return true;
            }
            //合格区间
            if (root.val < minValue || root.val > maxValue) {
                return false;
            }
            return dfs(root.left, minValue, root.val) && dfs(root.right, root.val, maxValue);
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
