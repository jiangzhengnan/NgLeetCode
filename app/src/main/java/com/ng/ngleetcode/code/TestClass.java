package com.ng.ngleetcode.code;

import com.ng.ngleetcode.base.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TestClass {

	public static void main(String[] args) {
		int[][] op = new int[][] { { 1, 1, 1 }, { 1, 2, 2 }, { 2, 1 }, { 1, 3, 3 }, { 2, 2 }, { 1, 4, 4 }, { 2, 1 },
				{ 2, 3 }, { 2, 4 }, };
		LogUtil.pring(LRU(op, 2));
	}

	static class Node {
		int key;
		int value;
		Node prev;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	static Map<Integer, Node> map = new HashMap();
	static Node head;
	static Node tail;
	static int mMapSize;

	public static int[] LRU(int[][] op, int k) {
		mMapSize = k;
		int resultSize = (int) Arrays.stream(op).filter(x -> x[0] == 2).count();
		head = new Node(-1, -1);
		tail = new Node(-1, -1);
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		int[] result = new int[resultSize];
		int resultIndex = 0;
		for (int[] opt : op) {
			if (opt[0] == 1) {
				set(opt[1], opt[2]);
			} else if (opt[0] == 2) {
				result[resultIndex] = get(opt[1]);
				resultIndex++;
			}
		}
		return result;
	}

	private static void set(int key, int value) {
		if (map.get(key) != null) {
			Node node = map.get(key);
			node.value = value;
			moveToHead(node, false);
		} else {
			if (map.size() >= mMapSize) {
				// �Ƴ�β��
				Node tailNode = tail.prev;
				tail.prev.prev.next = tail;
				tail.prev = tail.prev.prev;
				map.remove(tailNode.key);
			}
			// �嵽��ǰ��
			Node newNode = new Node(key, value);
			moveToHead(newNode, true);
			map.put(key, newNode);
		}
	}

	private static int get(int key) {
		Node node = map.get(key);
		if (node != null) {
			moveToHead(node, false);
			return node.value;
		} else {
			return -1;
		}
	}

	private static void moveToHead(Node newNode, boolean isNew) {
		if (!isNew) {
			newNode.prev.next = newNode.next;
			newNode.next.prev = newNode.prev;
		}
		Node preNext = head.next;
		head.next = newNode;
		newNode.prev = head;
		newNode.next = preNext;
		preNext.prev = newNode;
	}

}
