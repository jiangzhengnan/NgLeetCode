package com.ng.ngleetcode.code.other;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ng.ngleetcode.util.LogUtil;
import com.ng.ngleetcode.util.Node;
import com.ng.ngleetcode.util.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//空间和时间最好的选择吧。哈希表查+链表存
@Solution(easy = 1, hard = 1)
@RequiresApi(api = Build.VERSION_CODES.N)
public class 设计LRU缓存结构 {

	private static Map<Integer, Node> map = new HashMap<>();
	private static Node head = new Node(-1, -1);
	private static Node tail = new Node(-1, -1);
	private static int mapSize;

	public static int[] LRU(int[][] operators, int k) {
		mapSize = k;
		head.next = tail;
		tail.prev = head;
		int len = (int) Arrays.stream(operators).filter(x -> x[0] == 2).count();
		int[] res = new int[len];
		for (int i = 0, j = 0; i < operators.length; i++) {
			if (operators[i][0] == 1) {
				set(operators[i][1], operators[i][2]);
			} else {
				res[j++] = get(operators[i][1]);
			}
		}
		return res;
	}

	private static void set(int key, int val) {
		if (map.get(key) != null) {
			Node node = map.get(key);
			node.val = val;
			moveToHead(node);
		} else {
			if (map.size() == mapSize) {
				// 满了，移除最后一个
				int rk = tail.prev.key;
				tail.prev.prev.next = tail;
				tail.prev = tail.prev.prev;
				map.remove(rk);
			}
			Node node = new Node(key, val);
			map.put(key, node);
			moveToHead(node);
		}
	}

	private static int get(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.prev.next = node.next;
			node.next.prev = node.prev;
			moveToHead(node);
			return node.val;
		}
		return -1;
	}

	private static void moveToHead(Node node) {
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
		node.prev = head;
	}

	public static void main(String[] args) {
		int[][] op = new int[][] {
				{1,1,1},
				{1,2,2},
				{1,3,2},
				{2,1},
				{1,4,4},
				{2,2}
		};
		LogUtil.pring(LRU(op,3));
	}

}