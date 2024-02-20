package com.ng.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;

public class PracticeClass {

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

    private void test() {
        LogUtil.print(
            rightSideView(TreeNode.createTreeNode(1, 2, 3, -1, 5, -1, 4))
                .toString());
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //层序遍历
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);

        while (treeNodes.size() > 0) {
            int size = treeNodes.size();
            result.add(treeNodes.get(treeNodes.size() - 1).val);

            for (int i = 0; i < size; i++) {
                TreeNode tempNode = treeNodes.pop();
                if (tempNode.left != null) {
                    treeNodes.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    treeNodes.add(tempNode.right);
                }
            }
        }

        return result;
    }
}




