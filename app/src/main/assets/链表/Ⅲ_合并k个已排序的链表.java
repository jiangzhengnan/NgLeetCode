package com.ng.code.menu.链表;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.menu.Ⅰ_Ⅱ_Ⅲ_TemplateTestClass;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked
 * 原题描述:
 * 描述
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 *
 * 数据范围：节点总数 0 \le n \le 50000≤n≤5000，每个节点的val满足 |val| <= 1000∣val∣<=1000
 * 要求：时间复杂度 O(nlogn)O(nlogn)
 * 示例1
 * 输入：
 * [{1,2,3},{4,5,6,7}]
 * 返回值：
 * {1,2,3,4,5,6,7}
 *
 * 示例2
 * 输入：
 * [{1,2},{1,4,5},{6}]
 * 返回值：
 * {1,1,2,4,5,6}
 */
@Solution(easy = 0, hard = 1, particle = 1)
public class Ⅲ_合并k个已排序的链表 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        LogUtil.print(easySolution.mergeKLists(getData()));
        LogUtil.print(hardSolution.mergeKLists(getData()));
    }

    private static ListNode[] getData() {
        ListNode[] lists = new ListNode[]{
            ListNode.getNodeList(1, 2),
            ListNode.getNodeList(1, 4, 5),
            ListNode.getNodeList(1, 2)
        };
        return lists;
    }

    /**
     * 优先级队列
     */
    private static class EasySolution {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            PriorityQueue<ListNode> queue =
                new PriorityQueue<>(lists.length, (a, b) -> (a.val - b.val));

            for (ListNode temp : lists) {
                if (temp != null) {
                    queue.add(temp);
                }
            }
            ListNode ya = new ListNode(-1);
            ListNode t = ya;
            while (queue.size() > 0) {
                ListNode node = queue.poll();
                t.next = node;
                if (node.next != null) {
                    queue.add(node.next);
                }
                t = t.next;
            }
            return ya.next;
        }
    }

    /**
     * 时空间效率相比上面的其实更低，但是更加好理解
     * 归并大法
     */
    private static class HardSolution {

        public ListNode mergeKLists(ListNode[] lists) {
            return mergeKLists(lists, 0, lists.length - 1);
        }

        public ListNode mergeKLists(ListNode[] lists, int left, int right) {
            if (left > right) {
                return null;
            } else if (left == right) {
                return lists[left];
            }

            int mid = left + (right - left) / 2;
            ListNode one = mergeKLists(lists, left, mid);
            ListNode two = mergeKLists(lists, mid + 1, right);
            return merge(one, two);
        }

        private ListNode merge(ListNode n1, ListNode n2) {
            if (n1 == null) {
                return n2;
            }
            if (n2 == null) {
                return n1;
            }
            if (n1.val < n2.val) {
                n1.next = merge(n1.next, n2);
                return n1;
            } else {
                n2.next = merge(n2.next, n1);
                return n2;
            }
        }
    }
}
