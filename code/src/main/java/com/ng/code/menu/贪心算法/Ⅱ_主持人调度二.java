package com.ng.code.menu.贪心算法;

import com.ng.code.util.Solution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，第 i 个活动的开始时间是 starti ,
 * 第 i 个活动的结束时间是 endi ,举办某个活动就需要为该活动准备一个活动主持人。
 * 一位活动主持人在同一时间只能参与一个活动。并且活动主持人需要全程参与活动，
 * 换句话说，一个主持人参与了第 i 个活动，那么该主持人在 (starti,endi) 这个时间段不能参与其他任何活动。
 * 求为了成功举办这 n 个活动，最少需要多少名主持人。
 * 复杂度要求：时间复杂度 O(n \log n)O(nlogn) ，空间复杂度 O(n)O(n)
 *
 * 示例:
 * 示例1
 * 输入：
 * 2,[[1,2],[2,3]]
 * 返回值：
 * 1
 * 说明：
 * 只需要一个主持人就能成功举办这两个活动
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_主持人调度二 {

    public static void main(String[] args) {

    }


    /**
     * 优先队列
     * 首先对startEnd按开始时间从小到大排序，如果开始时间相同，则按结束时间排序。
     * 然后初始化一个优先队列。
     * 如果当前开始时间大于等于之前某个活动的结束时间，说明可以让之前那个主持人继续主持当前的活动；否则，将当前活动的结束时间入队，表示需要新派一个主持人。
     */
    private static class EasySolution {
        public int minmumNumberOfHost(int n, int[][] startEnd) {
            //如果开始时间相同，按结束时间从小到大排序，否则按开始时间排序
            Arrays.sort(startEnd, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

            //初始化优先队列
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int[] activity : startEnd) {
                //如果当前开始时间大于等于之前某个活动的结束时间，说明可以让之前那个主持人继续主持当前的活动
                if (!queue.isEmpty() && queue.peek() <= activity[0]) {
                    queue.poll();
                }
                //如果不满足，需要新派一个主持人
                queue.offer(activity[1]);
            }

            return queue.size();

        }
    }

    /**
     * 排序+贪心
     * 首先建立两个数组分别存储开始时间（记为start）和结束时间（记为end）。
     * 然后分别对start和end数组进行排序。
     * 接着遍历start数组，判断当前开始时间是否大于等于最小的结束时间，
     * 如果是，则说明当前主持人就可以搞定（对应当前最小的结束时间的那个活动）；
     * 如果否，则需要新增一个主持人，并将end数组下标后移（表示对应的活动已经有人主持）。
     */
    private static class HardSolution {

        public int minmumNumberOfHost(int n, int[][] startEnd) {
            //初始化两个数组，分别记录开始时间和结束时间
            int[] start = new int[n];
            int[] end = new int[n];

            //将活动的开始和结束时间赋值道start和end数组
            for (int i = 0; i < n; i++) {
                start[i] = startEnd[i][0];
                end[i] = startEnd[i][1];
            }

            //按从小到大的顺序对start和end数组排序
            Arrays.sort(start);
            Arrays.sort(end);

            int res = 0, index = 0;
            for (int i = 0; i < n; i++) {
                //如果大于等于当前最小的结束时间，说明当前主持人可以搞定
                if (start[i] >= end[index]) {
                    index++;
                }
                //否则，需要新增主持人
                else {
                    res++;
                }
            }

            return res;
        }
    }

}
