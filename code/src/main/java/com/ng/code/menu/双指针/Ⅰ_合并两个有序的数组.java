package com.ng.code.menu.双指针;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/merge-sorted-array/description/
 * 原题描述:
 * 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
 * 注意：
 * 1.保证 A 数组有足够的空间存放 B 数组的元素， A 和 B 中初始的元素数目分别为 m 和 n，A的数组空间大小为 m+n
 * 2.不要返回合并的数组，将数组 B 的数据合并到 A 里面就好了，且后台会自动将合并后的数组 A 的内容打印出来，所以也不需要自己打印
 * 3. A 数组在[0,m-1]的范围也是有序的
 *
 * 示例:
 * 输入：
 * [4,5,6],[1,2,3]
 * 返回值：
 * [1,2,3,4,5,6]
 * 说明：
 * A数组为[4,5,6]，B数组为[1,2,3]，后台程序会预先将A扩容为[4,5,6,0,0,0]，B还是为[1,2,3]，m=3，n=3，传入到函数merge里面，然后请同学完成merge函数，将B的数据合并A里面，最后后台程序输出A数组
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅰ_合并两个有序的数组 {

    public static void main(String[] args) {
        int[] a = new int[]{4, 5, 6, 0, 0, 0};
        int[] b = new int[]{1, 2, 3};
        EasySolution.merge(a, 3, b, 3);
        LogUtil.print(a);
    }

    private static class EasySolution {

        /**
         * 从右边开始往左插入，右边大，左边小
         */
        public static void merge(int[] nums1, int m, int[] nums2, int n) {
            if (nums2 == null || n == 0) {
                return;
            }

            int endIndex = nums1.length - 1;

            int nums1EndIndex = endIndex - n;
            int nums2EndIndex = n - 1;

            while (endIndex >= 0) {
                if (nums1EndIndex >= 0 && nums2EndIndex >= 0) {
                    if (nums1[nums1EndIndex] > nums2[nums2EndIndex]) {
                        nums1[endIndex--] = nums1[nums1EndIndex--];
                    } else {
                        nums1[endIndex--] = nums2[nums2EndIndex--];
                    }
                } else if (nums1EndIndex >= 0) {
                    nums1[endIndex--] = nums1[nums1EndIndex--];
                } else if (nums2EndIndex >= 0) {
                    nums1[endIndex--] = nums2[nums2EndIndex--];
                }
            }

        }


    }

    private static class HardSolution {

        /**
         * 简化太多
         */
        public static void merge(int A[], int m, int B[], int n) {
            int length = A.length;
            while (n > 0) {
                if (m > 0 && A[m - 1] > B[n - 1]) {
                    A[--length] = A[--m];
                } else {
                    A[--length] = B[--n];
                }
            }

        }
    }

}
