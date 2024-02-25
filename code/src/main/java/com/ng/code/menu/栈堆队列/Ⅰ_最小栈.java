package com.ng.code.menu.栈堆队列;

import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/min-stack/description/
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * <p>
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅰ_最小栈 {

    public static void main(String[] args) {
    }

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(n), 开辟了一个辅助栈。
     */
    private static class EasySolution {


        class MinStack {

            Stack<Integer> stackTotal = new Stack<Integer>();
            Stack<Integer> stackLittle = new Stack<Integer>();

            public void push(int node) {
                stackTotal.push(node);
                if (stackLittle.empty()) {
                    stackLittle.push(node);
                } else {
                    if (node <= stackLittle.peek()) {
                        stackLittle.push(node);
                    } else {
                        stackLittle.push(stackLittle.peek());
                    }
                }
            }

            public void pop() {
                stackTotal.pop();
                stackLittle.pop();
            }

            public int top() {
                return stackTotal.peek();
            }

            public int getMin() {
                return stackLittle.peek();
            }

        }

    }

    //同上
    private static class HardSolution {

    }

}
