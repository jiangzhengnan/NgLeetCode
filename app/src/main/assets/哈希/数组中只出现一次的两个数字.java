package com.ng.code.menu.哈希;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/389fc1c3d3be4479a154f63f495abff8?tpId=295&tqId=1375231&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * <p>
 * 原题描述:
 * 描述
 * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 数据范围：数组长度 2\le n \le 10002≤n≤1000，数组中每个数的大小 0 < val \le 10000000<val≤1000000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 提示：输出时按非降序排列。
 * 示例:
 * 示例1
 * 输入：
 * [1,4,1,6]
 * 返回值：
 * [4,6]
 * 说明：
 * 返回的结果中较小的数排在前面
 * 示例2
 * 输入：
 * [1,2,3,3,2,9]
 * 返回值：
 * [1,9]
 */
@Solution(easy = 1, hard = 0)
public class 数组中只出现一次的两个数字 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 4, 1, 6};
        int[] data2 = new int[]{1, 2, 3, 3, 2, 9};
        LogUtil.pring(EasySolution.FindNumsAppearOnce(data));
        LogUtil.pring(EasySolution.FindNumsAppearOnce(data2));
    }

    private static class EasySolution {

        public static int[] FindNumsAppearOnce(int[] array) {
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

        public static int[] FindNumsAppearOnce (int[] array) {

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
