package com.ng.code.menu.栈堆队列;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=23290&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 *
 * 示例:
 * 输入：
 * [1,2,3,4,5],[4,5,3,2,1]
 * 复制
 * 返回值：
 * true
 * 说明：
 * 可以通过push(1)=>push(2)=>push(3)=>push(4)=>pop()=>push(5)=>pop()=>pop()=>pop()=>pop()
 * 这样的顺序得到[4,5,3,2,1]这个序列，返回true
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_栈的压入弹出序列 {

    public static void main(String[] args) {
        int[] pushA = new int[]{2, 1, 0};
        int[] popA = new int[]{1, 2, 0};
        int[] popB = new int[]{4, 3, 5, 1, 2};

        LogUtil.print(EasySolution.IsPopOrder(pushA, popA));
        LogUtil.print(EasySolution.IsPopOrder(pushA, popB));

    }

    //双指针+辅助栈
    //On On
    private static class EasySolution {

        public static boolean IsPopOrder(int[] pushA, int[] popA) {
            int pushIndex = 0;
            int popIndex = 0;
            Stack<Integer> stack = new Stack<>();
            while (pushIndex < pushA.length) {
                stack.push(pushA[pushIndex ++ ]);
                while (popIndex < popA.length && !stack.empty() && stack.peek() == popA[popIndex]) {
                    stack.pop();
                    popIndex++;
                }
            }
            return popIndex == popA.length;

        }

    }

    private static class HardSolution {

    }

}
