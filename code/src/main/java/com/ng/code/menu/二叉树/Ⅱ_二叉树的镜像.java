package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/a9d0ecbacef9410ca97463e4a5c83be7?tpId=295&tqId=1374963&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 数据范围：二叉树的节点数 0 \le n \le 10000≤n≤1000 ， 二叉树每个节点的值 0\le val \le 10000≤val≤1000
 * 要求： 空间复杂度 O(n)O(n) 。本题也有原地操作，即空间复杂度 O(1)O(1) 的解法，时间复杂度 O(n)O(n)
 *
 * 输入：
 * {8,6,10,5,7,9,11}
 * 返回值：
 * {8,10,6,11,9,7,5}
 * 说明：
 * 如题面所示
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_二叉树的镜像 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(8, 6, 10, 5, 7, 9, 11);
        HardSolution.Mirror(data).print();
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
     *主要是利用栈（或队列）遍历树的所有节点 node ，并交换每个 node 的左 / 右子节点
     * 算法流程：
     * 1、特例处理： 当 pRoot为空时，直接返回 null ；
     * 2、初始化： 栈（或队列），本文用栈stack ，并加入根节点 pRoot。
     * 3、循环交换： 当栈 stack 为空时跳出；
     *       3.1、出栈： 记为 node ；
     *       3.2、添加子节点： 将 node 左和右子节点入栈；
     *       3.3、交换： 交换 node 的左 / 右子节点。
     * 4、返回值： 返回根节点 pRoot 。
     */
    private static class HardSolution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param pRoot TreeNode类
         * @return TreeNode类
         */
        public static TreeNode Mirror (TreeNode pRoot) {
            // write code here
            if(pRoot == null) return null;
            // 构建辅助栈
            Stack<TreeNode> stack = new Stack<>();
            // 根节点入栈
            stack.add(pRoot);
            while(!stack.isEmpty()) {
                // 节点出栈
                TreeNode node = stack.pop();
                // 根节点的左右子树入栈
                if(node.left != null) stack.add(node.left);
                if(node.right != null) stack.add(node.right);
                // 左右子树交换
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
            }
            return pRoot;
        }
    }

}
