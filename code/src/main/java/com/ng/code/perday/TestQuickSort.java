package com.ng.code.perday;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.BasePracticeClass;

public class TestQuickSort {

  public static void main(String[] args) {
    System.out.println("what");
    int[] data = new int[]{5, 6, 4, 7, 3, 8, 1, 9};
    LogUtil.print(data);

    sort(data, 0, data.length - 1);
    LogUtil.print(data);
  }

  private static void sort(int[] data, int start, int end) {
    int target = data[start];
    int left = start;
    int right = end;
    while (left < right) {
      while (left < right && data[left] < target) {
        left++;
      }
      while (left < right && data[right] > target) {
        right--;
      }
      if (data[left] == data[right]  ) {
        left++;
      } else {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
      }
    }
    if (left - 1 > start) {
      sort(data, start, left - 1);
    }
    if (end > right + 1) {
      sort(data, right + 1, end);
    }
  }


}
