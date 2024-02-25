package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=117&tqId=37819&rp
 * =1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7
 * %25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * <p>
 * 输入：
 * {1,2,3}
 * 前 中 后
 * [[1,2,3],[2,1,3],[2,3,1]]
 *
 * 难点：
 * 迭代实现 中序，后序？
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_实现二叉树先序中序和后序遍历 {

	public static void main(String[] args) {
		TreeNode root2 = TreeNode.createTreeNode(3, 9, 20, -1, -1, 15, 7);
		root2.print();
		LogUtil.print(threeOrders(root2));
	}

	public static int[][] threeOrders(TreeNode root) {
		// 存放三种遍历方式的结果
		int[][] result = new int[3][];
		List<Integer> preOrderList = new ArrayList<>();
		List<Integer> inOrderList = new ArrayList<>();
		List<Integer> postOrderList = new ArrayList<>();

		// 判断节点是否为空
		if (root == null) {
			// 如果节点为空，则返回空数组
			return new int[][]{};
		}

		// 创建一个栈用来存放节点
		Stack<TreeNode> nodeStack = new Stack<>();
		nodeStack.push(root);

		// 遍历节点
		while (!nodeStack.empty()) {
			TreeNode node = nodeStack.pop();
			preOrderList.add(node.val);

			// 把右节点压入栈中
			if (node.right != null) {
				nodeStack.push(node.right);
			}
			// 把左节点压入栈中
			if (node.left != null) {
				nodeStack.push(node.left);
			}
		}

		// 中序遍历
		nodeStack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !nodeStack.isEmpty()) {
			while (cur != null) {
				nodeStack.push(cur);
				cur = cur.left;
			}
			cur = nodeStack.pop();
			inOrderList.add(cur.val);
			cur = cur.right;
		}

		// 后续遍历 没看懂。。
		nodeStack = new Stack<>();
		cur = root;
		TreeNode pre = null;
		while (cur != null || !nodeStack.isEmpty()) {
			while (cur != null) {
				nodeStack.push(cur);
				cur = cur.left;
			}
			cur = nodeStack.peek().right;
			if (cur == null || cur == pre) {
				cur = nodeStack.pop();
				postOrderList.add(cur.val);
				pre = cur;
				cur = null;
			}
		}

		// 转换成int数组并返回
		result[0] = arrayListToArray(preOrderList);
		result[1] = arrayListToArray(inOrderList);
		result[2] = arrayListToArray(postOrderList);

		return result;
	}

	private static int[] arrayListToArray(List<Integer> cache) {
		int[] result = new int[cache.size()];
		for (int i = 0; i < cache.size(); i++) {
			result[i] = cache.get(i);
		}
		return result;
	}


}
