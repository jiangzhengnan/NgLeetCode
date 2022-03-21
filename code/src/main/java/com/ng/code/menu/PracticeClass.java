package com.ng.code.menu;

import com.ng.code.util.LogUtil;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/03/18
 * @description :
 */
public class PracticeClass {

    public static void main(String[] args) {
        int[] array = new int[]{9, 7, 6, 5, 3, 1, 10, 4, 9, 8, -1};
        LogUtil.pring(qSort(array));
    }

    public static int[] qSort(int[] array) {
        return qSort(array, 0, array.length - 1);
    }

    public static int[] qSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return array;
        }
        int temp = array[leftIndex];
        int left = leftIndex;
        int right = rightIndex;
        while (left < right) {
            while (array[right] > temp && left < right) {
                right--;
            }
            while (array[left] < temp && left < right) {
                left++;
            }
            if (array[left] == array[right] && left < right) {
                left++;
            } else {
                int temp2 = array[left];
                array[left] = array[right];
                array[right] = temp2;
            }
        }
        if (left - 1 > leftIndex) {
            array = qSort(array, leftIndex, left - 1);
        }
        if (right + 1 < rightIndex) {
            array = qSort(array, right + 1, rightIndex);
        }
        return array;
    }

}
