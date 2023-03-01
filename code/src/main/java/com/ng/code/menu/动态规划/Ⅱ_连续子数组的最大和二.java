package com.ng.code.menu.动态规划;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/11662ff51a714bbd8de809a89c481e21?tpId=13&tqId=2282583&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，找到一个具有最大和的连续子数组。
 * 1.子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 * 2.如果存在多个最大和的连续子数组，那么返回其中长度最长的，该题数据保证这个最长的只存在一个
 * 3.该题定义的子数组的最小长度为1，不存在为空的子数组，即不存在[]是某个数组的子数组
 * 4.返回的数组不计入空间复杂度计算
 * <p>
 * 示例:
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 返回值：
 * [3,10,-4,7,2]
 * 说明：
 * 经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18，故返回[3,10,-4,7,2]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_连续子数组的最大和二 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.FindGreatestSumOfSubArray(new int[]{-2, -8, -1, -5, -9}));

    }

    /**
     * 基于简单难度的升级版本
     * 保存一下子数组，并统计子数组状态
     * 时间on
     * 空间o1
     */
    private static class EasySolution {
        public static int[] FindGreatestSumOfSubArray(int[] array) {

            int result = -101;
            int now = 0;
            List<Integer> resultSubList = new ArrayList<>();

            List<Integer> subList = new ArrayList<>();
            for (int temp : array) {
                if (temp > now + temp) {
                    now = temp;
                    subList.clear();
                } else {
                    now += temp;
                }
                subList.add(temp);
                if (now >= result) {
                    result = now;
                    resultSubList = copy(subList);
                }
            }

            int[] resultList = new int[resultSubList.size()];
            for (int i = 0; i < resultSubList.size(); i++) {
                resultList[i] = resultSubList.get(i);
            }
            return resultList;
        }

        private static List<Integer> copy(List<Integer> subList) {
            List<Integer> resultSubList = new ArrayList<>();
            for (int temp : subList) {
                resultSubList.add(temp);
            }
            return resultSubList;
        }
    }

    //使用指针代替new一个新的list
    private static class HardSolution {

        public int[] FindGreatestSumOfSubArray(int[] array) {
            //动态规划
            int sum = array[0], num = array[0];
            //当前遍历位置子串首尾位置
            int start_tmp = 0, end_tmp = 1;
            //最大和子串首尾位置
            int start = 0, end = 1;
            for (int i = 1; i < array.length; i++) {
                //数组中包含i位置的连续串最大值（比较当前数组值与之前累加值大小）
                if (array[i] > num + array[i]) {
                    num = array[i];
                    start_tmp = i;
                    end_tmp = i + 1;
                } else {
                    num = num + array[i];
                    end_tmp++;
                }
                //记录并更新当前遍历数组的最大子串和
                if (num > sum || (num == sum) && (end_tmp - start_tmp) > (end - start)) {
                    sum = num;
                    start = start_tmp;
                    end = end_tmp;
                }
            }
            return Arrays.copyOfRange(array, start, end);
        }

    }

}
