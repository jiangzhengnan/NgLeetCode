package com.ng.code;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        ListNode node1 = ListNode.getNodeList(5, 9, 7, 5, 7, 1, 2, 6, 4, 2, 7, 8, 9, 6, 1, 6, 6, 1, 1, 4, 2, 9, 5, 5, 0, 4, 6, 3, 0, 4, 3, 5, 6, 7, 0, 5, 5, 4, 4, 0);
        ListNode node2 = ListNode.getNodeList(1, 3, 2, 5, 0, 6, 0, 2, 1, 4, 3, 9, 3, 0, 9, 9, 0, 3, 1, 6, 5, 7, 8, 6, 2, 3, 8, 5, 0, 9, 7, 9, 4, 5, 9, 9, 4, 9, 3, 6);
        LogUtil.print(addInList(node1, node2));
    }


    public static ListNode addInList(ListNode head1, ListNode head2) {

        return null;
    }


}



