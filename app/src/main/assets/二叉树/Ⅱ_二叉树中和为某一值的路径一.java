package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/508378c0823c423baa723ce448cbfd0c?tpId=295&tqId=634&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 *
 * 示例1
 * 输入：
 * {5,4,8,1,11,#,9,#,#,2,7},22
 * 返回值：
 * true
 */
@Solution(easy = 0, hard = 0)
public class Ⅱ_二叉树中和为某一值的路径一 {

    public static void main(String[] args) {
        LogUtil.print(HardSolution.hasPathSum(TreeNode.createTreeNode(5, 4, 8, 1, 11, -1, 9, -1, -1, 2, 7), 22));
    }

    /**
     * 递归+深度优先遍历
     */
    private static class EasySolution {

        static boolean result = false;

        public static boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            query(root, sum);
            return result;
        }

        public static void query(TreeNode node, int sum) {
            sum -= node.val;
            if (sum == 0 && node.left == null && node.right == null) {
                result = true;
            }
            if (node.left != null) {
                query(node.left, sum);
            }
            if (node.right != null) {
                query(node.right, sum);
            }
        }
    }

    /**
     * BFS 广度优先搜索 + 累减
     */
    private static class HardSolution {
        public static boolean hasPathSum(TreeNode root, int sum) {
            if (root == null)
                return false;
            Queue<TreeNode> queue = new LinkedList<>();
            root.val = sum - root.val;
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                //累减到根节点之后，结果为0，说明存在这样一条路径，直接返回true
                if (node.left == null && node.right == null && node.val == 0)
                    return true;
                //左子节点累减
                if (node.left != null) {
                    node.left.val = node.val - node.left.val;
                    queue.add(node.left);
                }
                //右子节点累减
                if (node.right != null) {
                    node.right.val = node.val - node.right.val;
                    queue.add(node.right);
                }
            }
            return false;
        }
    }

}
