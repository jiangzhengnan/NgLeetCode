package com.ng.code;

import com.ng.code.menu.二叉树.Ⅰ_合并二叉树;
import com.ng.code.menu.二叉树.Ⅱ_二叉搜索树与双向链表;
import com.ng.code.menu.二叉树.Ⅱ_二叉搜索树中第K小的元素;
import com.ng.code.menu.栈堆队列.Ⅱ_滑动窗口的最大值;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Node;
import com.ng.code.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PracticeClass {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(10, 6, 14, 4, 8, 12, 16);
        Convert(data);
    }

    public static TreeNode Convert(TreeNode pRootOfTree) {
        //先序
        loop(pRootOfTree);
        return root;
    }
    static TreeNode root = null;

    static TreeNode pre = null;
    private static void loop(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        loop(treeNode.left);
        if (root == null) {
            root = treeNode;
        }

        if (pre != null) {
            treeNode.left = pre;
            pre.right = treeNode;
        }
        pre = treeNode;

        loop(treeNode.right);
    }
}



