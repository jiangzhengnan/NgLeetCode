package com.ng.code;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        ListNode phead1 = ListNode.getNodeList(1, 2, 3);
        ListNode phead2 = ListNode.getNodeList(4, 5);
        ListNode phead3 = ListNode.getNodeList(6, 7);

        ListNode.mergeNodeList(phead1, phead3);
        ListNode.mergeNodeList(phead2, phead3);

        LogUtil.print(phead1);
        LogUtil.print(phead2);

        LogUtil.print(FindFirstCommonNode(phead1, phead2));
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode ta1 = pHead1;
        ListNode ta2 = pHead2;

        while (ta1 != null && ta2 != null) {
            if (ta1 == null) {
                ta1 = pHead2;
            } else {
                ta1 = ta1.next;
            }
            if (ta2 == null) {
                ta2 = pHead1;
            } else {
                ta2 = ta2.next;
            }
        }

        return null;
    }

}




