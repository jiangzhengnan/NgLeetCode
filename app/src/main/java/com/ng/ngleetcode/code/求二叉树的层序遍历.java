package com.ng.ngleetcode.code;

import com.ng.ngleetcode.base.LogUtil;
import com.ng.ngleetcode.base.TreeNode;

import java.util.ArrayList;

/**
 * 日期:
 * 原题链接: https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=117&tqId=37723&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * 完成次数: 1
 *
 * 描述:
 * 
 */
public class 求二叉树的层序遍历 {

	public static void main(String[] args) {
		LogUtil.pring(levelOrder(null).toString());
	}

	static ArrayList<ArrayList<Integer>> result = new ArrayList<>();


	public static ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
		if(root == null) {
			return result;
		}
		// write code here
		query(root, 0);
		return result;
	}

	public static void query(TreeNode root , int K) {
		if(result.size() < K +1) {
			result.add(new ArrayList<>());
		}
		ArrayList<Integer> currentList = result.get(K);
		currentList.add(root.val);

		if (root.left != null) {
			query(root.left, K+1);
		}
		if (root.right != null) {
			query(root.right, K+1);
		}
	}
}
