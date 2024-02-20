package com.ng.code.menu.哈希;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/single-number-iii/description/
 * 原题描述:
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * 示例 1：
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 * 输入：nums = [0,1]
 */
@Solution(easy = 1, hard = 0, partice = 1)
public class Ⅱ_只出现一次的数字 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 4, 1, 6};
        int[] data2 = new int[]{1, 2, 3, 3, 2, 9};
        LogUtil.print(EasySolution.singleNumber(data));
        LogUtil.print(EasySolution.singleNumber(data2));
    }

    /**
     * 用桶计算
     */
    private static class EasySolution {

        public static int[] singleNumber(int[] array) {
            int[] result = new int[2];
            int nowIndex = 0;
            HashMap<Integer, Integer> tong = new HashMap<>();
            for (int temp : array) {
                if (tong.containsKey(temp)) {
                    tong.put(temp, tong.get(temp) + 1);
                } else {
                    tong.put(temp, 1);
                }
            }
            Iterator<Integer> iterator = tong.keySet().iterator();
            while (iterator.hasNext()) {
                int key = iterator.next();
                int value = tong.get(key);
                if (value == 1) {
                    result[nowIndex] = key;
                    nowIndex++;
                }
            }
            return result;
        }

    }

    /**
     * 位运算
     */
    private static class HardSolution {

        public static int[] singleNumber (int[] array) {

            // 先将全部数进行异或运算，得出最终结果
            int tmp = 0;
            for(int num: array){
                tmp ^= num;
            }

            // 找到那个可以充当分组去进行与运算的数
            // 从最低位开始找起
            int mask = 1;
            while((tmp&mask) == 0){
                mask <<= 1;
            }

            // 进行分组，分成两组，转换为两组 求出现一次的数字 去求
            int a = 0;
            int b = 0;
            for(int num:array){
                if((num&mask) == 0){
                    a ^= num;
                }else{
                    b ^= num;
                }
            }
            // 因为题目要求小的数放前面，所以这一做个判断
            if(a > b){
                int c = a;
                a = b;
                b = c;
            }
            return new int[]{a,b};
        }
    }

}
