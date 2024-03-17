package com.ng.code;

import java.util.Stack;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        ListNode data = ListNode.getNodeList(1, 1, 2, 1);
        LogUtil.print(isPalindrome(data));
    }

    public static boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode tempHead = head;
        while (tempHead != null) {
            stack.push(tempHead);
            tempHead = tempHead.next;
        }

        int size = stack.size();
        for (int i = 0; i < size / 2; i++) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}




