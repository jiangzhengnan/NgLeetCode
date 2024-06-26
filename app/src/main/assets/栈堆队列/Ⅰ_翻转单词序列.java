package com.ng.code.menu.栈堆队列;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13&tqId=23287&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“nowcoder. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a nowcoder.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *
 * 示例:
 * 输入：
 * "nowcoder. a am I"
 * 返回值：
 * "I am a nowcoder."
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅰ_翻转单词序列 {

    public static void main(String[] args) {
        String data = "nowcoder. a am I";
        LogUtil.print(EasySolution.ReverseSentence(data));
    }

    //栈的基本使用
    private static class EasySolution {

        public static String ReverseSentence(String str) {
            Stack<String> result = new Stack<>();
            for (String temp : str.split(" ")) {
                result.push(temp);
            }
            StringBuilder sb = new StringBuilder();
            while (!result.empty()) {
                sb.append(result.pop() + (result.empty() ? "":" "));
            }
            return sb.toString();
        }

    }

    private static class HardSolution {

    }

}
