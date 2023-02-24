package com.ng.base;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val +"";
    }

    public void print() {
        TreeOperation.show(this);
    }

    public static TreeNode createTreeNode(int... array) {
        TreeNode root = createTreeNode(1, array);
        return root;
    }

    private static TreeNode createTreeNode(int index, int... array) {
        if (index > array.length) {
            return null;
        }
        Integer value = array[index - 1];
        if (value == null || value == -1) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = createTreeNode(index * 2, array);
        node.right = createTreeNode(index * 2 + 1, array);
        return node;
    }
}
