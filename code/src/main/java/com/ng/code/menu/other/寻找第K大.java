package com.ng.code.menu.other;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=117&tqId=37791&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117%26page%3D1&difficulty=undefined&judgeStatus=undefined&tags=&title=
 *
 *
 */
@Solution(easy = 0, hard = 0)
public class 寻找第K大 {

	public static void main(String[] args) {
		LogUtil.pring(findKth(new int[] { 1, 3, 5, 2, 2 }, 5, 3));

	}

	// 每次找出最大的，设为-1
	public static int findKth(int[] a, int n, int K) {
		return qsort(a, 0, a.length - 1, K);
	}

	public static int qsort(int[] arr, int left, int right, int k) {
		int p = getIndex(arr, left, right);
		// 改进后，很特殊的是，p是全局下标，只要p对上topK坐标就可以返回
		if (p == arr.length - k) {
			return arr[p];
		} else if (p < arr.length - k) {
			// 如果基准在左边，这在右边找
			return qsort(arr, p + 1, right, k);
		} else {
			return qsort(arr, left, p - 1, k);
		}
	}

	public static int getIndex(int arr[], int i, int j) {
		int pivot = arr[i];
		while (i < j) {
			while ((i < j) && (arr[j] > pivot)) {
				j--;
			}
			while ((i < j) && (arr[i] < pivot)) {
				i++;
			}

			if ((arr[i] == arr[j]) && (i < j)) {
				i++;
			} else {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		return i;
	}

}