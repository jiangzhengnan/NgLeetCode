package com.ng.code.menu.链表;

import java.util.HashSet;
import java.util.Set;

import com.ng.base.utils.ListNode;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2
 * &envId=top
 * 原题描述:
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0
 * 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * 示例:
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅱ_环形链表 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> tong = new HashSet<>();
            while (head != null) {
                if (tong.contains(head)) {
                    return head;
                }
                tong.add( head);
                head = head.next;
            }
            return null;
        }
    }

    private static class HardSolution {
        /**
         * 快慢指针
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head;
            while (fast != null) {
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {
                    return null;
                }
                if (fast == slow) {
                    ListNode ptr = head;
                    while (ptr != slow) {
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    return ptr;
                }
            }
            return null;
        }
    }

}
