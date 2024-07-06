package com.ng.code.menu.模拟;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.Map;


//空间和时间最好的选择吧。哈希表查+链表存

/**
 * https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150
 */
@Solution(easy = 0, hard = 0)
@RequiresApi(api = Build.VERSION_CODES.N)
public class Ⅱ_设计LRU缓存结构 {

	class LRUCache {

		class Node {
			public int key, val;
			public Node prev, next;

			public Node(int key, int val) {
				this.key = key;
				this.val = val;
				this.prev = null;
				this.next = null; // 初始化为 null
			}
		}

		private HashMap<Integer, Node> map = new HashMap<>();
		private Node head = new Node(0, 0); // 初始化为 0
		private Node tail = new Node(0, 0); // 初始化为 0
		private int capacity;

		public LRUCache(int capacity) {
			this.capacity = capacity;
			head.next = tail;
			tail.prev = head;
		}

		public int get(int key) {
			Node cur = map.get(key);
			if (cur != null) {
				moveToHead(cur);
				return cur.val;
			}
			return -1;
		}


		public void put(int key, int value) {
			Node cur = map.get(key);
			if (cur != null) {
				// 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
				cur.val = value;
				moveToHead(cur);
			} else {
				cur = new Node(key, value);
				map.put(key, cur);
				if (map.size() > capacity) {
					// 移除最后一个
					Node res = tail.prev;
					removeNode(res);
					map.remove(res.key);
				}
			}
		}

		private void moveToHead(Node node) {
			removeNode(node);
			node.prev = head;
			node.next = head.next;
			head.next.prev = node;
			head.next = node;
		}

		private void removeNode(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}

	}

}