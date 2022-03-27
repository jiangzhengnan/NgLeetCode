package com.ng.code.menu.双指针;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 *
 * 要求：空间复杂度 O(n) ，时间复杂度 O(nlogn)
 * 进阶：空间复杂度 O(val) 时间复杂度O(val)
 *
 * 示例:
 * 输入：
 * [[10,30],[20,60],[80,100],[150,180]]
 * 返回值：
 * [[10,60],[80,100],[150,180]]
 */
@Solution(easy = 1, hard = 1, partice = 0)
public class Ⅱ_合并区间 {

    public static void main(String[] args) {
        ArrayList<Interval> data = new ArrayList<>();
        data.add(new Interval(1, 4));
        data.add(new Interval(0, 0));
        LogUtil.pring(EasySolution.merge(data).toString());
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start +
                    ", " + end +
                    ']';
        }
    }

    private static class EasySolution {
        static ArrayList<Interval> result = new ArrayList<>();

        public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
            Collections.sort(intervals,(a, b)->a.start-b.start);

            for (Interval input : intervals) {

                boolean needAdd = true;
                for (int i = 0; i < result.size(); i++) {
                    Interval temp = result.get(i);
                    if (input.end < temp.start || input.start > temp.end) {
                        continue;
                    } else {
                        temp.start = Math.min(temp.start, input.start);
                        temp.end = Math.max(temp.end, input.end);
                        needAdd = false;
                    }
                }
                if (needAdd)
                result.add(input);

            }


            return result;
        }
    }

    private static class HardSolution {

    }

}
