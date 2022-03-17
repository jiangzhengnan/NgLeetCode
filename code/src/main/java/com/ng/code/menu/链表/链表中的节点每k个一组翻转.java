package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=117&tqId=37746&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * 描述:
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 *
 * 数据范围：  2000 0≤n≤2000 ，  20001≤k≤2000 ，链表中每个元素都满足 0   10000≤val≤1000
 * 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 例如：
 * 给定的链表是  1→2→3→4→5
 * 对于 k = 2k=2 , 你应该返回 2→1→4→3→5
 * 对于 k = 3k=3 , 你应该返回 3→2→1→4→5
 */
@Solution(easy = 0, hard = 0)
public class 链表中的节点每k个一组翻转 {

	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		List<ListNode> map = new ArrayList();
		while(true) {
			if(!insert(map, pHead1)) {
				return pHead1;
			}
			if(!insert(map, pHead2)) {
				return pHead2;
			}

			if(pHead1.next == null && pHead2.next == null) {
				break;
			}

			if(pHead1.next != null) {
				pHead1 = pHead1.next;
			}
			if(pHead2.next != null) {
				pHead2 = pHead2.next;
			}

		}

		return null;
	}

	public boolean insert(List<ListNode> map, ListNode temp) {
		if(map.contains(temp)) {
			return false;
		} else {
			map.add(temp);
			return true;
		}
	}

	public static void main(String[] args) {
		LogUtil.pring(reverseGroup(ListNode.getNodeList(new int[]{1, 2, 3, 4, 5}), 2));
	}

	// pre ->start        end -> next
	// pre ->
	public static ListNode reverseGroup(ListNode head, int k) {
		//创建哑结点
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode end = dummy;
		while (end.next != null) {
			for (int i = 0; i < k && end != null; i++) {
				end = end.next;
			}
			if (end == null) {
				break;
			}
			//开始的节点
			ListNode start = pre.next;
			//下一次反转开始的节点
			ListNode next = end.next;
			//断开 k链表 最后一个节点
			end.next = null;
			// 把k 链表之前的节点和反转后的k链表相连接
			pre.next = reverse(start);
			//反转之后，start变成尾巴节点，所以这里用start连接喜爱一个节点
			start.next = next;

			pre = start;
			end = start;
		}
		return dummy.next;
	}

	private static ListNode reverse(ListNode list) {
		ListNode pre = null;
		ListNode now = list;
		while (now != null) {
			ListNode next = now.next;
			now.next = pre;
			pre = now;
			now = next;
		}
		return pre;
	}


}
