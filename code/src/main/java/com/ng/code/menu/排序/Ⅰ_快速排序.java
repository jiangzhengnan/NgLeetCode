package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

@Solution(easy = 0, hard = 0)
public class Ⅰ_快速排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 7, 6, 5, 3, 1, 10, 4, 9, 8, -1};
        qSort(array);
        LogUtil.print(array);
    }

    public static void qSort(int[] array) {
        qsort(array, 0, array.length - 1);
    }

    /**
     * 1.从数列中挑出一个元素，称为 “基准”（pivot）;
     * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * 口诀：基准在左，先扫右；基准在右，先扫左。最后记得把基准从"寄存点"换回"分界点"。
     */
    public static void qsort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int target = array[start];

        while (left < right) {
            // 重点！先从右边扫，再从左边扫
            while (left < right && array[right] >= target) {
                right--;
            }
            while (left < right && array[left] <= target) {
                left++;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        // 重点！基准值要换到正确的位置
        array[start] = array[left];
        array[left] = target;

        qsort(array, start, left - 1);
        qsort(array, right + 1, end);


    }

}
