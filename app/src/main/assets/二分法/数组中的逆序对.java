package com.ng.code.menu.二分法;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=295&tqId=23260&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007
 * <p>
 * 数据范围：  对于 50\%50% 的数据, size<=q 10^4 size≤10
 * 4
 * <p>
 * 对于 100\%100% 的数据, size<=q 10^5size≤10
 * 5
 * <p>
 * 数组中所有数字的值满足 0 <= val <= 10000000≤val≤1000000
 * <p>
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
 * 输入描述：
 * 题目保证输入的数组中没有的相同的数字
 * 示例1
 * 输入：
 * [1,2,3,4,5,6,7,0]
 * 返回值：7
 */
@Solution(easy = 0, hard = 0)
public class 数组中的逆序对 {

    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,5,6,7,0};
        LogUtil.pring(HardSolution.InversePairs(data));

    }

    private static class EasySolution {

    }

    /**
     * 就是利用归并排序的思想进行逆序对的记数。因为每次进行归并排序，最后并的过程都会比较两个元素的大小，因此此时可以计算逆序对的个数
     */
    private static class HardSolution {
        static int cnt = 0;

        public static int InversePairs(int[] array) {
            if (array.length != 0) {
                divide(array, 0, array.length - 1);
            }
            return cnt;
        }

        public static void divide(int[] array, int start, int end) {
            //递归终止条件
            if (start >= end) return;
            //取中
            int mid = start + (end - start) / 2;
            //分
            divide(array, start, mid);
            divide(array, mid + 1, end);
            //治
            merge(array, start, mid, end);
        }

        public static void merge(int[] array, int start, int mid, int end) {
            //临时数组
            int[] tmp = new int[end - start + 1];
            LogUtil.pring(array);
            //i和j表示两个分数组的左下标，k表示临时数组的当前下标
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                //如果前小于后，则存前，前右移
                if (array[i] <= array[j]) {
                    tmp[k++] = array[i++];
                }
                //如果前大于后，则存后，后右移-------***此时存在逆序对，要进行比较
                else {
                    tmp[k++] = array[j++];
                    //如果此时前大于后，那么现有前到最后的元素都会大于后
                    cnt = (cnt + mid - i + 1) % 1000000007;
                }
            }
            //未遍历完的直接放在右侧
            while (i <= mid) {
                tmp[k++] = array[i++];
            }
            while (j <= end) {
                tmp[k++] = array[j++];
            }
            //将临时数组的值覆盖原来数组
            for (k = 0; k < tmp.length; k++) {
                array[start + k] = tmp[k];
            }
        }

    }

}
