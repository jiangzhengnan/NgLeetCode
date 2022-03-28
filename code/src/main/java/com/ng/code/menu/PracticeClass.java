package com.ng.code.menu;

import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/03/18
 * @description :
 */
public class PracticeClass {

    public static void main(String[] args) {
        TreeNode data1 = TreeNode.createTreeNode(1, 2, 3, 4, 5, 6, 7);
        TreeNode data2 = TreeNode.createTreeNode(1, 2, 3, 4, 5, -1, 6);
        LogUtil.pring( isCompleteTree(data1));
    }

    //层序遍历
    private static boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> data = new LinkedList<>();
        data.add(root);
        while (!data.isEmpty()) {
            TreeNode now = data.poll();
            LogUtil.pring(now == null ? "null" : now.val+"");
            if (now != null) {
                data.add(now.left);
                data.add(now.right);
            }

        }

        return true;
    }


}
