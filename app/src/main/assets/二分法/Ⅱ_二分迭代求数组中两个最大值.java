package com.ng.code.menu.二分法;

import java.util.Random;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 从数组 A[lo,hi) 中找出最大的两个正整数 A[x1] ,A[x2];要求元素比较的次数尽可能得少
 * <p>
 * 示例:
 * 示例 1：
 * 输入：nums = [29,5,4,6,3,7,10,2,1,11,19,13,15]
 * 输出：{29,19};
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_二分迭代求数组中两个最大值 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        int numSize = 100000;
        int[] data = new int[numSize + 1];
        for (int i = 0; i < numSize; i++) {
            int value = new Random().nextInt(numSize);
            data[i] = value;
        }

        //LogUtil.print(easySolution.max2InArray(data));

        int times = 10000;

        LogUtil.calculateTime("my", times, new LogUtil.Function() {
            @Override
            public void fun() {
                easySolution.max2InArray(data);
            }
        });


        LogUtil.calculateTime("easy", times, new LogUtil.Function() {
            @Override
            public void fun() {
                int[] res2 = {0, 0};
                easySolution.max2Dich4(data, 0, data.length, res2); //调用函数，传入的需要时 引用数据类型，才能 在下面打印 最大值和 次大值；否则，函数内对参数的改变只能在函数内部体现，无法传递到函数外部，也就是说 最大值和次大值打印结果为0，0
            }
        });

        LogUtil.calculateTime("hard", times, new LogUtil.Function() {
            @Override
            public void fun() {
                hardSolution.max2InArray(data);
            }
        });

    }

    private static class EasySolution {
        /**
         * 二分+递归
         */
        public int[] max2InArray(int[] data) {
            int[] res = new int[2];
            loopQuery(data, 0, data.length - 1, res);
            return res;
        }

        /**
         * res 0最大，1第二大
         */
        private void loopQuery(final int[] data, final int start, final int end, final int[] res) {
            if (start >= end) {
                return;
            }
            int length = end - start;
            LogUtil.print("length:" + length);
            if (length == 2) {
                //如果只剩两个
                res[0] = Math.max(data[start], data[end]);
                res[1] = Math.min(data[start], data[end]);

            } else if (length == 3) {
                //如果只剩三个
                res[0] = Math.max(data[start], Math.max(data[end - 1], data[end]));
                if (res[0] == data[start]) {
                    res[1] = Math.max(data[end - 1], data[end]);
                } else if (res[0] == data[end - 1]) {
                    res[1] = Math.max(data[start], data[end]);
                } else {
                    res[1] = Math.max(data[end - 1], data[start]);
                }

            } else {
                int mid = (start + end) / 2;
                loopQuery(data, start, mid, res);
                loopQuery(data, mid, end, res);
            }
        }


        public void max2Dich4(int[] A, int lo, int hi, int[] res) {

            if (lo + 2 == hi) { //数组中只有两个元素时
                res[0] = Math.max(A[lo], A[hi - 1]);
                res[1] = Math.min(A[lo], A[hi - 1]);

            } else if (lo + 3 == hi) { //数组有3个元素时
                res[0] = Math.max(A[lo], Math.max(A[lo + 1], A[hi - 1]));

                if (res[0] == A[lo]) {
                    res[1] = Math.max(A[lo + 1], A[hi - 1]);
                } else if (res[0] == A[lo + 1]) {
                    res[1] = Math.max(A[lo], A[hi - 1]);
                } else {
                    res[1] = Math.max(A[lo], A[lo + 1]);
                }

            } else {
                int mi = (lo + hi) / 2;
                int x1L = 0;
                int x2L = 0;
                int x1R = 0;
                int x2R = 0;

                max2Dich4(A, lo, mi, res);//左边数组求最大和第二大
                x1L = res[0];
                x2L = res[1];//这一步是把得到的值付给 左边的最大值，左边的次大值，java 和c++引用的使用上有所不同（c++的话，可以直接把 x1L的引用传入函数中，但是java 直接传 x1L的话，并不会改变 实参 x1L的值），所以这里需要 把 res 的值 赋给 x1L变量，才能进行下面的比较
                max2Dich4(A, mi, hi, res);//右边数组求最大和第二大
                x1R = res[0];
                x2R = res[1];//这一步是把得到的值付给 右边的最大值，右边的次大值
                if (x2L >= x1R) {  //如果左边的 第二大 > 右边的 第一大
                    res[0] = x1L;
                    res[1] = x2L;
                } else if (x2R >= x1L) {  //如果左边的 第一大 < 右边的 第二大
                    res[0] = x1R;
                    res[1] = x2R;
                } else {
                    if (x1L > x1R) {
                        res[0] = x1L;
                        res[1] = Math.max(x2L, x1R);

                    } else {
                        res[0] = x1R;
                        res[1] = Math.max(x1L, x2R);
                    }
                }

            }

        }


    }

    /**
     * 这个居然是最快的方式..
     */
    private static class HardSolution {

        public int[] max2InArray(int[] data) {
            int max = 0;
            int second = 0;
            for (int temp : data) {
                second = max;
                if (temp > max) {
                    max = temp;
                }
            }
            return new int[]{max, second};
        }

    }

}
