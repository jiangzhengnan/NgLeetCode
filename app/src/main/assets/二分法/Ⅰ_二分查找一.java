package com.ng.code.menu.二分法;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/d3df40bd23594118b57554129cadf47b?tpId=295&tqId=1499549&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * -1, 0, 3, 4, 6, 10, 13, 14     13
 * 输出 6
 */
@Solution(easy = 0, hard = 0, partice = 1)
public class Ⅰ_二分查找一 {

    public static void main(String[] args) {
        LogUtil.print(HardSolution.search(new int[]{-1, 0, 3, 4, 6, 10, 13, 14}, 13));
    }

    /**
     * 递归
     */
    private static class EasySolution {
        public static int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            // write code here
            return search(nums, target, 0, nums.length);
        }

        public static int search(int[] nums, int target, int start, int end) {
            if (start > end) {
                return -1;
            }
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                mid--;
                return search(nums, target, start, mid);
            } else {
                mid++;
                return search(nums, target, mid, end);
            }
        }
    }

    /**
     * 迭代
     */
    private static class HardSolution {

        public static int search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return -1;
            }
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return nums[r] == target ? r : -1;

        }
    }


}
