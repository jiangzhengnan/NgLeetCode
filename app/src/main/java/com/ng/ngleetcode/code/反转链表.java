package com.ng.ngleetcode.code;

import com.ng.ngleetcode.base.ListNode;
import com.ng.ngleetcode.base.LogUtil;

/**
 * 完成:
 *
 *
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 *
 * 数据范围： \ 0 \le n \le 2000 0≤n≤2000 ， 1 \le k \le 20001≤k≤2000 ，链表中每个元素都满足 0
 * \le val \le 10000≤val≤1000 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n) 例如： 给定的链表是
 * 1\to2\to3\to4\to5 1→2→3→4→5 对于 k = 2k=2 , 你应该返回 2\to 1\to 4\to 3\to 5
 * 2→1→4→3→5 对于 k = 3k=3 , 你应该返回 3\to2 \to1 \to 4\to 5 3→2→1→4→5
 *
 * 1→2→3→4→5 2 2→1→4→3→5
 * 1→2→3→4→5 3 3→2→1→4→5
 *
 */
public class 反转链表 {

	public static void main(String[] args) {
		ListNode data = ListNode.getNodeList(new int[] { 1, 2, 3, 4, 5 });
		LogUtil.pring(reverseKGroup(data ,2 ));
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		//边界条件判断
		if (head == null || head.next == null)
			return head;
		ListNode tail = head;
		for (int i = 0; i < k; i++) {
			//剩余数量小于k的话，则不需要反转。
			if (tail == null)
				return head;
			tail = tail.next;
		}
		// 反转前 k 个元素
		ListNode newHead = reverse(head, tail);
		//下一轮的开始的地方就是tail
		head.next = reverseKGroup(tail, k);
		return newHead;
	}

	/*
        链表的反转，不是反转全部，只反转区间[head,tail)中间的节点，左闭右开区间
     */
	private static ListNode reverse(ListNode head, ListNode tail) {
		ListNode pre = null;
		ListNode next = null;
		while (head != tail) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}


}