package com.ng.code.menu.排序;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

@Solution(easy = 0, hard = 0, partice = 0)
public class 快速排序 {

	public static void main(String[] args) {
		int[] array = new int[] { 9, 7, 6, 5, 3, 1, 10, 4,9, 8, -1 };
		LogUtil.pring(qSort(array));
	}

	public static int[] qSort(int[] array) {

		return qsort(array,0,array.length-1);
	}

	public static int[] qsort(int arr[],int start,int end) {
		int pivot = arr[start];
		int i = start;
		int j = end;

		while (i<j) {
			while ((i<j)&&(arr[j]>pivot)) {
				j--;
			}
			while ((i<j)&&(arr[i]<pivot)) {
				i++;
			}
			if ((arr[i]==arr[j])&&(i<j)) {
				i++;
			} else {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}

		}
		if (i-1>start) arr=qsort(arr,start,i-1);
		if (j+1<end) arr=qsort(arr,j+1,end);
		return (arr);
	}

}
