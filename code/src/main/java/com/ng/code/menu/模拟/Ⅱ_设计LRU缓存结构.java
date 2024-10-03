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
			public Node pre, next;

			public Node(int key, int val) {
				this.key = key;
				this.val = val;
				this.pre = null;
				this.next = null; // 初始化为 null
			}
		}

		private int size;
		private Node head = new Node(-1, -1);
		private Node tail = new Node(-1, -1);
		private Map<Integer, Node> data = new HashMap();

		public LRUCache(int capacity) {
			size = capacity;
			head.next = tail;
			tail.pre = head;
		}

		public int get(int key) {
			Node node = data.get(key);
			if (node == null) {
				return -1;
			}
			moveToHead(node);
			return node.val;
		}

		public void put(int key, int value) {
			Node node = data.get(key);
			if (node != null) {
				node.val = value;
				moveToHead(node);
			} else {
				node = new Node(key, value);
				data.put(key, node);
				moveToHead(node);
				if (data.size() > size) {
					Node last = tail.pre;
					removeNode(last);
					data.remove(last.key);
				}
			}
		}

		private void removeNode(Node node) {
			if (node.pre != null && node.next != null) {
				node.pre.next = node.next;
				node.next.pre = node.pre;
			}
		}

		private void moveToHead(Node node) {
			removeNode(node);

			node.pre = head;
			node.next = head.next;
			node.next.pre = node;
			head.next = node;
		}
	}

}