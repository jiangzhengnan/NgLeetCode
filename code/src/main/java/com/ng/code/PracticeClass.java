package com.ng.code;

import java.util.Stack;

import com.ng.code.util.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        LogUtil.pring(minStack.getMin());   // 返回 -3.
        minStack.pop();
        LogUtil.pring(minStack.getMin());   // 返回 -2.

    }

    static class MinStack {
        Stack<Integer> mStackA;
        Stack<Integer> mStackB;

        public MinStack() {
            mStackA = new Stack<>();
            mStackB = new Stack<>();
            mStackB.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            mStackA.push(val);
            mStackB.push(Math.min(mStackB.peek(), val));
        }

        public void pop() {
            mStackA.pop();
            mStackB.pop();
        }

        public int top() {
            return mStackA.peek();
        }

        public int getMin() {
            return mStackB.peek();
        }

    }


}

