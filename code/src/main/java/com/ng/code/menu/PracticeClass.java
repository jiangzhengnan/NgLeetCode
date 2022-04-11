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
        ListNode data = ListNode.getNodeList(1, 1);
        LogUtil.pring(deleteDuplication(data));
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode ya = new ListNode(-1);
        ya.next = pHead;

        ListNode pre = ya;
        ListNode now = pHead;
        ListNode next = now.next;

        while (next != null) {

            if (now.val == next.val) {
                while (next != null && now.val == next.val) {
                    next = next.next;
                }
                pre.next = next;
            } else {
                pre = now;
            }
            now = next;

            if (next != null) {
                next = next.next;
            }
        }


        return ya.next;
    }


}
