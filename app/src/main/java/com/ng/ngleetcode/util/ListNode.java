package com.ng.ngleetcode.util;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode getNodeList(int[] array) {
        if (array == null) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            ListNode tempNode = new ListNode(array[i]);
            cur.next = tempNode;
            cur = tempNode;
        }
        return head;
    }

    @Override
    public String toString() {
        String result = "{";
        ListNode node = this;
        while (node != null) {
            result += node.val + ",";
            node = node.next;
        }
        return result.substring(0, result.length() - 1) + "}";
    }
}