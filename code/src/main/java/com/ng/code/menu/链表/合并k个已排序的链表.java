package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6?tpId=295&tqId=724&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 *
 * 数据范围：节点总数 0 \le n \le 50000≤n≤5000，每个节点的val满足 |val| <= 1000∣val∣<=1000
 * 要求：时间复杂度 O(nlogn)O(nlogn)
 * 示例1
 * 输入：
 * [{1,2,3},{4,5,6,7}]
 * 返回值：
 * {1,2,3,4,5,6,7}
 *
 * 示例2
 * 输入：
 * [{1,2},{1,4,5},{6}]
 * 返回值：
 * {1,1,2,4,5,6}
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 合并k个已排序的链表 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.mergeKLists(getData()));
        LogUtil.pring(HardSolution.mergeKLists(getData()));
    }

    private static ArrayList<ListNode> getData() {
        ArrayList<ListNode> lists = new ArrayList<>();
        lists.add(ListNode.getNodeList(new int[]{1, 2}));
        lists.add(ListNode.getNodeList(new int[]{1, 4, 5}));
        lists.add(ListNode.getNodeList(new int[]{6}));
        return lists;
    }

    /**
     * 循环遍历arraylist列表,进行比较,每次选出最小的节点加在结果链表里,
     * 然后列表位置的最小节点指向它自己的next节点. 直到列表里全是null,则终止循环.
     */
    private static class EasySolution {

        public static ListNode mergeKLists(ArrayList<ListNode> lists) {
            ListNode result = new ListNode(0);
            ListNode cur = result;
            int len = lists.size();
            if (len == 0) return null;
            while (true) {
                int minIndex = 0;//最小节点索引
                int minValue = Integer.MAX_VALUE;
                for (int i = 0; i < len; i++) {
                    if (lists.get(i) == null) {
                        continue;
                    }
                    if (lists.get(i).val < minValue) {
                        minIndex = i;
                        minValue = lists.get(i).val;
                    }
                }
                //更新结果
                ListNode node = new ListNode(0);
                node.val = minValue;
                cur.next = node;
                cur = cur.next;
                //更新原列表,使最小值的链表指向下一个节点
                lists.set(minIndex, lists.get(minIndex).next);
                //如果全是null，是的话就终止循环
                ListNode flag = null;
                for (int i = 0; i < len; i++) {
                    if (lists.get(i) != null) {
                        flag = lists.get(i);
                    }
                }
                if (flag == null) {
                    break;
                }
            }
            return result.next;
        }
    }

    /**
     * 分治+递归
     * 1、将两个链表合并。（递归方法合并）
     * 2、分而治之！求一个mid,将mid左边的合并，右边的合并，最后将左右两边的链表合并。
     * 3、重复这一过程，直到获取最终的有序链表。
     */
    private static class HardSolution {
        public static ListNode mergeKLists(ArrayList<ListNode> lists) {
            return mergeList(lists, 0, lists.size() - 1);
        }

        private static ListNode mergeList(ArrayList<ListNode> lists, int left, int right) {
            if (left == right) return lists.get(left);
            if (left > right) return null;
            int mid = left + (left + right) / 2;
            return merge(mergeList(lists, left, mid), mergeList(lists, mid + 1, right));
        }

        private static ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val > l2.val) {
                l2.next = merge(l1, l2.next);
                return l2;
            } else {
                l1.next = merge(l1.next, l2);
                return l1;
            }
        }
    }
}
