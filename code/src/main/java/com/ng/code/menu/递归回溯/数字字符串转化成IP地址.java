package com.ng.code.menu.递归回溯;

import com.ng.code.util.Solution;

import java.util.ArrayList;

/**
 * 原题描述:
 * 现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
 * 例如：
 * 给出的字符串为"25525522135",
 * 返回["255.255.22.135", "255.255.221.35"]. (顺序没有关系)
 *
 * 示例:
 * 示例1
 * 输入：
 * "25525522135"
 * 返回值：
 * ["255.255.22.135","255.255.221.35"]
 *
 * 示例2
 * 输入：
 * "1111"
 * 返回值：
 * ["1.1.1.1"]
 *
 * 示例3
 * 输入：
 * "000256"
 * 返回值：
 * "[]"
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 数字字符串转化成IP地址 {

    public static void main(String[] args) {

    }

    /**
     * 回溯+剪枝
     * 递归遍历字符串的每一位，由于每一位只可能是以下两种情况：与前面几位组成未满255，仍能继续往后结合的，
     * 与前面几位组成未满255，需要在该位后面加'.'的。因此，在递归种把这两种情况分离即可。采用一个list记录下每个'.'之间的数字，
     * 并且结束递归时，这个list的长度应该为4，因为IP地址有4段。最后每个字符串中，符合IP地址的字符串一定比原串长度大3（因为加了3个'.'），
     */
    private static class EasySolution {
        ArrayList<String> res = new ArrayList<>();

        public ArrayList<String> restoreIpAddresses(String s) {
            func(0, 0, s, new ArrayList<Integer>());
            return res;
        }

        public void func(int index, int cur, String s, ArrayList<Integer> templist) {
            if (index == s.length()) {
                if (templist.size() == 4) {
                    String str = "";
                    for (int i = 0; i < templist.size(); ++i) {
                        if (templist.size() - 1 != i)
                            str += templist.get(i) + ".";
                        else str += templist.get(i);
                    }
                    if (str.length() != s.length() + 3) return;
                    res.add(str);
                }
                return;
            }
            int temp = cur * 10 + s.charAt(index) - '0';
            if (temp >= 0 && temp <= 255) {
                func(index + 1, temp, s, new ArrayList<Integer>(templist));
                templist.add(temp);
                func(index + 1, 0, s, new ArrayList<Integer>(templist));
            }
        }

    }

    private static class HardSolution {

    }

}
