package com.ng.code.menu.排序;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : 
 * @creation : 2022/04/16
 * @description :
 * 算法复杂度：O(nlogn)
 * 算法空间复杂度：O(1)
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_桶排序 {

    public static void main(String[] args) {
        int[] array = new int[]{99, 88, 77, 33, 25, 14, 45, 55, 61};
        sort(array);
        LogUtil.print(array);
    }

    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //找出最大值，最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int length = nums.length;
        //桶的数量
        int bucketCount = (max - min) / length + 1;
        int[][] bucketArrays = new int[bucketCount][];
        //遍历数组，放入桶内
        for (int i = 0; i < length; i++) {
            //找到桶的下标
            int index = (nums[i] - min) / length;
            LogUtil.print("下表:" + index);
            //添加到指定下标的桶里，并且使用插入排序排序
            bucketArrays[index] = insertSortArrays(bucketArrays[index], nums[i]);
        }
        int k = 0;
        //合并全部桶的
        for (int[] bucketArray : bucketArrays) {
            if (bucketArray == null || bucketArray.length == 0) {
                continue;
            }
            for (int i : bucketArray) {
                //把值放回到nums数组中
                nums[k++] = i;
            }
        }
    }

    //每个桶使用插入排序进行排序
    private static int[] insertSortArrays(int[] arr, int num) {
        LogUtil.print(arr);
        if (arr == null || arr.length == 0) {
            return new int[]{num};
        }
        //创建一个temp数组，长度是arr数组的长度+1
        int[] temp = new int[arr.length + 1];
        //把传进来的arr数组，复制到temp数组
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        //找到一个位置，插入，形成新的有序的数组
        int i;
        for (i = temp.length - 2; i >= 0 && temp[i] > num; i--) {
            temp[i + 1] = temp[i];
        }
        //插入需要添加的值
        temp[i + 1] = num;
        //返回
        LogUtil.print(temp);
        return temp;
    }
}
