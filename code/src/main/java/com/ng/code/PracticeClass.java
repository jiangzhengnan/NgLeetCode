package com.ng.code;

import java.util.ArrayList;
import java.util.List;

import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;
import com.ng.code.util.BasePracticeClass;

public class PracticeClass extends BasePracticeClass {

    @Override
    public void run() {
        TreeNode root = TreeNode.createTreeNode(1, 1);
        TreeNode subRoot = TreeNode.createTreeNode(1);
        LogUtil.print(isSubtree(root, subRoot));
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val == subRoot.val &&
            isSubtree(root.left, subRoot.left) &&
            isSubtree(root.right, subRoot.right)
        ) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

}
