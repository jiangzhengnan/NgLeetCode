package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=117&tqId=37819&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 *
 * 输入：
 * {1,2,3}
 * 前 中 后
 * [[1,2,3],[2,1,3],[2,3,1]]
 */
@Solution(easy = 1, hard = 0)
public class Ⅰ_实现二叉树先序中序和后序遍历 {

	public static void main(String[] args) {
		TreeNode root2 = TreeNode.createTreeNode(1,2,3,4,5,6,7,8);
		root2.print();
		LogUtil.pring(threeOrders(root2));
	}

	public static List<Integer> cache = new ArrayList<>();

	public static int[][] threeOrders(TreeNode root) {
		if (root == null) {
			return new int[3][0];
		}
		int[] xianList = queryTree(root, 1);
		int[][] result = new int[3][xianList.length];
		result[0] = xianList;
		cache.clear();
		result[1] = queryTree(root, 2);
		cache.clear();
		result[2] = queryTree(root, 3);

		return result;
	}

	private static int[] queryTree(TreeNode root, int type) {
		switch (type) {
			case 1:
				cache.add(root.val);
				if (root.left != null) {
					queryTree(root.left, type);
				}
				if (root.right != null) {
					queryTree(root.right, type);
				}
				break;
			case 2:
				if (root.left != null) {
					queryTree(root.left, type);
				}
				cache.add(root.val);
				if (root.right != null) {
					queryTree(root.right, type);
				}
				break;

			case 3:
				if (root.left != null) {
					queryTree(root.left, type);
				}
				if (root.right != null) {
					queryTree(root.right, type);
				}
				cache.add(root.val);
				break;
		}
		return arrayListToArray(cache);
	}

	private static int[] arrayListToArray(List<Integer> cache) {
		int[] result = new int[cache.size()];
		for (int i = 0; i < cache.size(); i++) {
			result[i] = cache.get(i);
		}
		return result;
	}


}
