package com.ng.code.menu;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ⅰ Ⅱ Ⅲ
 * 动态规划
 * 递归回溯
 * 贪心算法
 */
public class PracticeClass {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(2, 1, 3, 5, 6, 4, 7);

        LogUtil.pring(oddEvenList(data));
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode ji = head;
        ListNode ou = head.next;
        ListNode tail = ou;

        while (ou != null && ou.next != null) {
            ji.next = ou.next;
            ji = ji.next;
            ou.next = ji.next;
            ou = ou.next;
        }
        ji.next = tail;
        return head;
    }

}
