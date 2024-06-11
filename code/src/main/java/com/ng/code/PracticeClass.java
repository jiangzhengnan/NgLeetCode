package com.ng.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.ng.base.utils.LogUtil;
import com.ng.base.utils.TreeNode;

public class PracticeClass {

    private void test() {

        //LogUtil.print(findMin(new int[]{3, 4, 5, 1, 2}));

        LogUtil.print(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = 0;
        int mid = 0;
        //先求左边界
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                first = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        //再求右边界
        left = 0;
        right = nums.length - 1;
        int second = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                second = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return new int[]{first, second};
    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}
