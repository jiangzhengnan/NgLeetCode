package com.ng.code.menu;

import com.ng.code.util.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        int[] data = new int[]{9, 7, 6, 5, 3, 1, 10, 4, 9, 8, -1};
        sort(data, 0, data.length - 1);
        LogUtil.pring(data);
    }

    public static void sort(int[] array, int start, int end) {
        int temp = array[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && array[right] > temp) {
                right--;
            }
            while (left < right && array[left] < temp) {
                left++;
            }
            if ((array[left] == array[right]) && (left < right)) {
                left++;
            } else {
                int temp1 = array[left];
                array[left] = array[right];
                array[right] = temp1;
            }
        }
        if (start < left - 1) {
            sort(array, start, left - 1);
        }
        if (right + 1 < end) {
            sort(array, right + 1, end);
        }
    }

}

