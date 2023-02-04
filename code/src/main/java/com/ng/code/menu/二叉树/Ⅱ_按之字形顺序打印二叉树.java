package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=295&tqId=23454&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 *
 * 输入：
 * {8,6,10,5,7,9,11}
 * 返回值：
 * [[8],[10,6],[5,7,9,11]]
 */
@Solution(easy = 1, hard = 1, partice = 1)
public class Ⅱ_按之字形顺序打印二叉树 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(8, 6, 10, 5, 7, 9, 11);
        data.print();
        LogUtil.pring(EasySolution.Print(data).toString());
    }

    /**
     * 深度优先搜索(DFS) 广度优先搜索(BFS)
     * 这里使用数组实现BFS
     * list.add(T): 按照索引顺序从小到大依次添加
     * list.add(index, T): 将元素插入index位置，index索引后的元素依次后移,这就完成了每一行元素的倒序，或者使用Collection.reverse()方法倒序也可以
     */
    private static class EasySolution {

        public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
            LinkedList<TreeNode> q = new LinkedList<>();
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            boolean rev = true;
            q.add(pRoot);
            while (!q.isEmpty()) {
                int size = q.size();
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node == null) {
                        continue;
                    }
                    if (rev) {
                        list.add(node.val);
                    } else {
                        list.add(0, node.val);
                    }
                    q.offer(node.left);
                    q.offer(node.right);
                }
                if (list.size() != 0) {
                    res.add(list);
                }
                rev = !rev;
            }
            return res;
        }


    }

    //同简单难度
    private static class HardSolution {

    }

}
