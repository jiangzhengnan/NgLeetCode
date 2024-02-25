package com.ng.code;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;

public class PracticeClass {

    private void test() {
        int[] data = new int[]{1, 2, 3, 1};
        TreeNode root2 = TreeNode.createTreeNode(3, 9, 20, -1, -1, 15, 7);

        LogUtil.print(levelOrder(root2).toString());

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> tong = new LinkedList<>();
        tong.push(root);

        while (!tong.isEmpty()) {
            int size = tong.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = tong.pop();
                tempList.add(tmp.val);
                if (tmp.left != null) {
                    tong.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    tong.addLast(tmp.right);
                }
            }
            res.add(tempList);
        }

        return res;
    }


    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}