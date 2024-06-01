package com.ng.code;


import java.util.Comparator;
import java.util.PriorityQueue;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {

        PriorityQueue<ListNode> pq = new PriorityQueue<>(100, (a, b) -> (a.val - b.val));
        pq.add(new ListNode(5));
        pq.add(new ListNode(4));

        pq.add(new ListNode(1));
        pq.add(new ListNode(2));

        pq.add(new ListNode(3));
        while (pq.size() > 0) {
            LogUtil.print(pq.poll());
        }
    }



    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}

/*


 */
