package com.ng.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;

public class PracticeClass {


    private void test() {
        ListNode phead1 = ListNode.getNodeList(1,2,3,4, 5);


        LogUtil.print(reverseBetween(phead1, 2, 4));

    }

    //快慢指针

    /**
     * 输入：
     * {1,2,3,4,5},2,4
     * 复制
     * 返回值：
     * {1,4,3,2,5}
     * <p>
     * 0   ... left  start  ... end  right ... n
     *
     * 3- 5
     *
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // write code here
        ListNode
            left = null,
            right = head,
            start = head,
            end = null;
        while (--m > 0) {
            left = start;
            start = start.next;
        }
        while (n-- > 0) {
            end = right;
            right = right.next;
        }
        if (right != null) {
            end.next = null;
        }
        ListNode newHead = revers(start);
        if (left != null) {
            left.next = newHead;
        } else {
            head = newHead;
        }
        if (right != null) {
            start.next = right;
        }

        return head;
    }

    private ListNode revers(ListNode node) {
        ListNode pre = null;
        ListNode now = node;
        ListNode next = null;
        while (now != null) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}




