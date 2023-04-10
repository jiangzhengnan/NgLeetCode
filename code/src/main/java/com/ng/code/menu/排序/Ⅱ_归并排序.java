package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : 
 * @creation : 2022/04/16
 * @description :
 * 算法复杂度：O(nlogn)
 * 算法空间复杂度：O(n)
 * 算法稳定性：稳定
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_归并排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.print(array);
    }

    private static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(array, start, mid);
        sort(array, mid + 1, end);
        merge(array, start, end);
    }

    private static void merge(int[] array, int start, int end) {
        int mid = (start + end) / 2;
        int left = start;
        int right = mid + 1;
        int[] newArray = new int[end - start + 1];
        int index = 0;
        while (left <= mid && right <= end) {
            if (array[left] > array[right]) {
                newArray[index++] = array[left++];
            } else {
                newArray[index++] = array[right++];
            }
        }
        while (left <= mid) {
            newArray[index++] = array[left++];
        }
        while (right <= end) {
            newArray[index++] = array[right++];
        }
        for (int i = 0; i < index; i++) {
            array[start + i] = newArray[i];
        }
    }
}
