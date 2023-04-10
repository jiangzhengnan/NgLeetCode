package com.ng.code.menu.哈希;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/345e2ed5f81d4017bbb8cc6055b0b711?tpId=295&tqId=731&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 描述
 * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 数据范围：0 \le n \le 30000≤n≤3000，数组中各个元素值满足 |val | \le 100∣val∣≤100
 * 空间复杂度：O(n^2)O(n2)，时间复杂度 O(n^2)O(n2)
 *
 * 注意：
 * 三元组（a、b、c）中的元素可以按任意顺序排列。
 * 解集中不能包含重复的三元组。
 *
 * 示例:
 * 示例1
 * 输入：
 * [-10,0,10,20,-10,-40]
 * 返回值：
 * [[-10,-10,20],[-10,0,10]]
 *
 * 示例2
 * 输入：
 * [-2,0,1,1,2]
 * 返回值：
 * [[-2,0,2],[-2,1,1]]
 *
 * 示例3
 * 输入：
 * [0,0]
 * 返回值：
 * []
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_三数之和 {

    public static void main(String[] args) {
        int[] data = new int[]{-10, 0, 10, 20, -10, -40};
        LogUtil.print(EasySolution.threeSum(data).toString());
    }

    /**
     * 双指针
     * 时间On2
     * 空间O1
     */
    private static class EasySolution {
        public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
            //存放最终答案的二维数组
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            int len = num.length;
            //特判：长度<3的数组不满足条件
            if (len < 3) {
                return ans;
            }
            //排序O(nlogn)
            Arrays.sort(num);

            for (int i = 0; i < len; i++) {
                //如果nums[i]已经大于0，就没必要继续往后了，因为和就是0啊
                if (num[i] > 0) {
                    return ans;
                }
                //注意考虑越界i>0,主要功能是排除重复值
                if (i > 0 && num[i] == num[i - 1]) {
                    continue;
                }
                //声明指针
                int cur = num[i];
                int left = i + 1;
                //从尾部开始
                int right = len - 1;
                while (left < right) {
                    //满足条件的三数和
                    int tp_ans = cur + num[left] + num[right];
                    //如果已经找到和为0
                    if (tp_ans == 0) {
                        //创建一个数组，并将满足条件的三元素放进去
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(cur);
                        list.add(num[left]);
                        list.add(num[right]);
                        //将最终的结果存入答案数组ans中
                        ans.add(list);
                        //判断是left指针指向是否重复
                        while (left < right && num[left] == num[left + 1]) {
                            left++;
                        }
                        //判断是right指针指向是否重复
                        while (left < right && num[right] == num[right - 1]) {
                            right--;
                        }
                        //移动指针
                        left++;
                        right--;
                    } else if (tp_ans < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * hash表去重
     */
    private static class HardSolution {

        public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
            //存放答案的集合
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            int len = num.length;
            //排序O(nlogn)
            Arrays.sort(num);
            //哈希表去重
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(num[i], i);
            }
            //若干变量声明
            int L, M, R;
            for (int i = 0; i < len; i = map.get(L) + 1) {
                //指定L的值
                L = num[i];
                //注意里层循环从i+1开始
                for (int j = i + 1; j < len; j = map.get(M) + 1) {
                    M = num[j];
                    //注意一下，这里是个容易错的细节..
                    R = -L - M;
                    if (R < M) {
                        break;
                    }
                    if (map.get(R) != null && map.get(R) > j) {
                        //创建一个数组，并将满足条件的三元素放进去
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(L);
                        list.add(M);
                        list.add(R);
                        //将最终的结果存入答案数组ans中
                        ans.add(list);
                        //不知道为什么。一下这种写法再牛客平台编译过不了，已经向官方反馈。
                        //ans.add(Arrays.asList(L,M,R));
                    }
                }
            }
            return ans;
        }
    }

}
