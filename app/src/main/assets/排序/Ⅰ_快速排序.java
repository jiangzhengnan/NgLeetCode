package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_快速排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 7, 6, 5, 3, 1, 10, 4, 9, 8, -1};
        LogUtil.print(qSort(array));
    }

    public static int[] qSort(int[] array) {

        return qsort(array, 0, array.length - 1);
    }

    public static int[] qsort(int arr[], int start, int end) {
        int pivot = arr[start];
        int left = start;
        int right = end;

        while (left < right) {
            while ((left < right) && (arr[right] > pivot)) {
                right--;
            }
            while ((left < right) && (arr[left] < pivot)) {
                left++;
            }
            if ((arr[left] == arr[right]) && (left < right)) {
                left++;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }

        }
        if (left - 1 > start) {
            arr = qsort(arr, start, left - 1);
        }
        if (right + 1 < end) {
            arr = qsort(arr, right + 1, end);
        }
        return (arr);
    }

}
