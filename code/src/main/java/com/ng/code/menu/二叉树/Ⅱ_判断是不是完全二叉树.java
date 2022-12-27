package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/8daa4dff9e36409abba2adbe413d6fae?tpId=295&tqId=2299105&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个二叉树，确定他是否是一个完全二叉树。
 *
 * 完全二叉树的定义：若二叉树的深度为 h，除第 h 层外，其它各层的结点数都达到最大个数，第 h 层所有的叶子结点都连续集中在最左边，这就是完全二叉树。（第 h 层可能包含 [1~2h] 个节点）
 * 数据范围：节点数满足 1 \le n \le 100 \1≤n≤100
 * 示例1
 * 输入：
 * {1,2,3,4,5,6}
 * 返回值：
 * true
 * 示例2
 * 输入：
 * {1,2,3,4,5,6,7}
 * 返回值：
 * true
 * 示例3
 * 输入：
 * {1,2,3,4,5,#,6}
 * 返回值：
 * false
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_判断是不是完全二叉树 {

    public static void main(String[] args) {
        TreeNode data1 = TreeNode.createTreeNode(1, 2, 3, 4, 5, 6, 7);
        TreeNode data2 = TreeNode.createTreeNode(1, 2, 3, 4, 5, -1, 6);
        LogUtil.pring(EasySolution.isCompleteTree(data1));
        LogUtil.pring(EasySolution.isCompleteTree(data2));
    }

    /**
     * 通过[层序遍历] Deque<TreeNode> d = new ArrayDeque<>();
     */
    private static class EasySolution {

        public static boolean isCompleteTree(TreeNode root) {
            // write code here
            if (root == null) {
                return false;
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            boolean ended = false;

            while (!q.isEmpty()) {
                TreeNode pop = q.poll();
                if (pop == null) {
                    ended = true;
                } else {
                    if (ended) return false;
                    q.offer(pop.left);
                    q.offer(pop.right);
                }
            }
            return true;
        }

    }

    /**
     * 同上
     */
    private static class HardSolution {

    }

}
