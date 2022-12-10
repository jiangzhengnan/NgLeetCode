package com.ng.code;

import com.ng.code.menu.链表.Ⅲ_单链表的排序;
import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PracticeClass {

    //nowtodo: 240 , 还差15个题
    //https://leetcode.cn/problem-list/2ckc81c/?difficulty=MEDIUM&page=1
    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 3, 2, 4, 5);
        //LogUtil.pring(trailingZeroes(5));
        int[] arrays = new int[]{2, 1, 1, 2};

        LogUtil.pring(kthSmallest(TreeNode.createTreeNode(5, 3, 6, 2, 4, -1, -1, 1), 3));
    }


    static List<Integer> list = new ArrayList<>();

    public static int kthSmallest(TreeNode root, int k) {
        loop(root);
        return list.get(k - 1);
    }

    private static void loop(TreeNode root) {
        if (root.left != null) {
            loop(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            loop(root.right);
        }
    }

//    public static int kthSmallest(TreeNode root, int k) {
//        LinkedList<TreeNode> tong = new LinkedList<>();
//
//        tong.add(root);
//
//        while (tong.size() > 0) {
//            int tempSize = tong.size();
//
//            for (int i = 0; i < tempSize; i++) {
//                TreeNode node = tong.pollFirst();
//                if (node.left != null) {
//                    tong.add(node.left);
//                }
//                if (node.right != null) {
//                    tong.add(node.right);
//                }
//            }
//
//
//        }
//
//
//        return -1;
//    }

}













