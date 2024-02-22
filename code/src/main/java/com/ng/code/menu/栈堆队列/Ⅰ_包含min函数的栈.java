package com.ng.code.menu.栈堆队列;

import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=295&tqId=23268&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。
 *
 * 此栈包含的方法有：
 * push(value):将value压入栈中
 * pop():弹出栈顶元素
 * top():获取栈顶元素
 * min():获取栈中最小元素
 *
 * 数据范围：操作数量满足 0 \le n \le 300 \0≤n≤300  ，输入的元素满足 |val| \le 10000 \∣val∣≤10000
 * 进阶：栈的各个操作的时间复杂度是 O(1)\O(1)  ，空间复杂度是 O(n)\O(n)
 *
 * 示例:
 * 输入:    ["PSH-1","PSH2","MIN","TOP","POP","PSH1","TOP","MIN"]
 * 输出:    -1,2,1,-1
 * 解析:
 * "PSH-1"表示将-1压入栈中，栈中元素为-1
 * "PSH2"表示将2压入栈中，栈中元素为2，-1
 * “MIN”表示获取此时栈中最小元素==>返回-1
 * "TOP"表示获取栈顶元素==>返回2
 * "POP"表示弹出栈顶元素，弹出2，栈中元素为-1
 * "PSH1"表示将1压入栈中，栈中元素为1，-1
 * "TOP"表示获取栈顶元素==>返回1
 * “MIN”表示获取此时栈中最小元素==>返回-1
 *
 * 示例1
 * 输入：
 *  ["PSH-1","PSH2","MIN","TOP","POP","PSH1","TOP","MIN"]
 * 返回值：
 * -1,2,1,-1
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅰ_包含min函数的栈 {

    public static void main(String[] args) {
    }

    /**
     * 时间复杂度：O(1)
     * 空间复杂度：O(n), 开辟了一个辅助栈。
     */
    private static class EasySolution {

        Stack<Integer> stackTotal = new Stack<Integer>();
        Stack<Integer> stackLittle = new Stack<Integer>();

        public void push(int node) {
            stackTotal.push(node);
            if(stackLittle.empty()){
                stackLittle.push(node);
            }else{
                if(node <= stackLittle.peek()){
                    stackLittle.push(node);
                }else{
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

        public int min() {
            return stackLittle.peek();
        }

    }

    //同上
    private static class HardSolution {

    }

}
