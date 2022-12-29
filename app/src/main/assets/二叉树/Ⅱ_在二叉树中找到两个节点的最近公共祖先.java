package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/e0cc33a83afe4530bcec46eba3325116?tpId=295&tqId=1024325&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 *
 * 数据范围：树上节点数满足 1 \le n \le 10^5 \1≤n≤10
 * 5
 * , 节点值val满足区间 [0,n)
 * 要求：时间复杂度 O(n)O(n)
 *
 * 注：本题保证二叉树中每个节点的val值均不相同。
 *
 * 输入：
 * {3,5,1,6,2,0,8,#,#,7,4},5,1
 * 返回值：
 * 3
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_在二叉树中找到两个节点的最近公共祖先 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(3, 5, 1, 6, 2, 0, 8, -1, -1, 7, 4);
        LogUtil.pring(EasySolution.lowestCommonAncestor(data, 5, 1));
    }

    /**
     * 非递归写法
     * 1.把每个节点都遍历一遍，然后顺便记录他们的父节点存储在Map中
     * 2.然后找公共节点
     * 3.这里可以通过BFS，遍历到指定的层数就可以退出了
     * 时间复杂度：O（n），n是二叉树节点的个数，最坏情况下每个节点都会被访问一遍
     * 空间复杂度：O（n），一个是BFS需要的队列，一个是父子节点关系的map
     */
    private static class EasySolution {

        public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
            //记录遍历到的每个节点的父节点。
            Map<Integer, Integer> parent = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            parent.put(root.val, Integer.MIN_VALUE);//根节点没有父节点，给他默认一个值
            queue.add(root);
            //直到两个节点都找到为止。
            while (!parent.containsKey(o1) || !parent.containsKey(o2)) {
                //队列是一边进一边出，这里poll方法是出队，
                TreeNode node = queue.poll();
                if (node.left != null) {
                    //左子节点不为空，记录下他的父节点
                    parent.put(node.left.val, node.val);
                    //左子节点不为空，把它加入到队列中
                    queue.add(node.left);
                }
                //右节点同上
                if (node.right != null) {
                    parent.put(node.right.val, node.val);
                    queue.add(node.right);
                }
            }
            Set<Integer> ancestors = new HashSet<>();
            //记录下o1和他的祖先节点，从o1节点开始一直到根节点。
            while (parent.containsKey(o1)) {
                ancestors.add(o1);
                o1 = parent.get(o1);
            }
            //查看o1和他的祖先节点是否包含o2节点，如果不包含再看是否包含o2的父节点……
            while (!ancestors.contains(o2))
                o2 = parent.get(o2);
            return o2;

        }

    }

    /**
     * 使用递归写法改进
     */
    private static class HardSolution {
        public static int lowestCommonAncestor(TreeNode root, int o1, int o2) {
            return helper(root, o1, o2).val;
        }

        public static TreeNode helper(TreeNode root, int o1, int o2) {
            if (root == null || root.val == o1 || root.val == o2)
                return root;
            TreeNode left = helper(root.left, o1, o2);
            TreeNode right = helper(root.right, o1, o2);
            //如果left为空，说明这两个节点在root结点的右子树上，我们只需要返回右子树查找的结果即可
            if (left == null)
                return right;
            //同上
            if (right == null)
                return left;
            //如果left和right都不为空，说明这两个节点一个在root的左子树上一个在root的右子树上，
            //我们只需要返回cur结点即可。
            return root;
        }
    }

}
