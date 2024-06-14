package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/path-sum/description/?envType=study-plan-v2&envId=top-interview-150
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

        /**
         * 假定从根节点到当前节点的值之和为 val，我们可以将这个大问题转化为一个小问题：
         * 是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
         *
         * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val
         * 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
         * 若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
         */
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null) {
                return sum == root.val;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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
