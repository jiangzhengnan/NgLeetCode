package com.ng.code.menu.栈堆队列;

import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=295&tqId=23457&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 
 * 原题描述:
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 * 数据范围：数据流中数个数满足 1 \le n \le 1000 \1≤n≤1000  ，大小满足 1 \le val \le 1000 \1≤val≤1000
 * 进阶： 空间复杂度 O(n) \O(n)  ， 时间复杂度 O(nlogn) \O(nlogn)
 * 
 * 示例:
 * 输入：
 * [5,2,3,4,1,6,7,0,8]
 * 
 * 返回值：
 * "5.00 3.50 3.00 3.50 3.00 3.50 4.00 3.50 4.00 "
 * 
 * 说明：
 * 数据流里面不断吐出的是5,2,3...,则得到的平均数分别为5,(5+2)/2,3...   
 * 示例2
 * 输入：
 * [1,1,1]
 * 
 * 返回值：
 * "1.00 1.00 1.00 "
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_数据流中的中位数 {

    public static void main(String[] args) {

    }

    /**
     * 插入的时候排序，所以很容易想到利用堆排序，每次加入一个元素就调整堆。因为第4种使用到了堆，所以这里选择（在网上扒了）另一种插入排序的方法，二分法。
     */
    private static class EasySolution {
        private List<Integer> list = new LinkedList();

        public void Insert(Integer num) {
            if (list.size() == 0) {
                list.add(num);
                return;
            }
            int first = 0;
            int last = list.size() - 1;
            int mid = 0;
            while (first <= last) {
                mid = (first + last) / 2;
                if (list.get(mid) > num)
                    last = mid - 1;
                else
                    first = mid + 1;
            }
            list.add(first, num);
            return;
        }

        public Double GetMedian() {
            int index = list.size();
            if (index % 2 == 1) {
                return (double) list.get(index / 2);
            }
            return ((double) (list.get(index / 2 - 1)) + (double) list.get(index / 2)) / 2;
        }
    }

    /**
     * 让树给我们排序，然后我们再取中位数，但是值得注意的是，在Set集合中，没有get方法，所以无法直接获取某个角标所对应的元素，通过查资料，发现需要将Set转换成List即可，因此需要再处理一次。
     */
    private static class HardSolution {
        public TreeSet<Integer> tree = new TreeSet<>();

        public void Insert(Integer num) {
            tree.add(num);
        }

        public Double GetMedian() {
            ArrayList<Integer> list = new ArrayList<>();
            list.addAll(tree);
            int index = list.size();
            if (index % 2 == 1) {
                return (double) list.get(index / 2);
            }
            return ((double) (list.get(index / 2 - 1)) + (double) list.get(index / 2)) / 2;
        }
    }

}
