package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶(n为正整数)总共有多少种跳法。
 * <p>
 * 示例:
 * 输入：
 * 3
 * 返回值：
 * 4
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_跳台阶高级 {

    public static void main(String[] args) {

        LogUtil.print(2 << 1);
    }

    /**
     * /f(n)=f(n-1)+f(n-2)+...+f(0)
     */
    private static class EasySolution {
        public int jumpFloorII(int target) {
            if (target <= 1) {
                return target;
            }
            int result = 1;
            for (int i = 1; i < target; i++) {
                result += jumpFloorII(target - i);
            }

            return result;

        }
    }

    /**
     * f[n] = f[n-1] + f[n-2] + ... + f[0]
     * 那么f[n-1] 为多少呢？
     * f[n-1] = f[n-2] + f[n-3] + ... + f[0]
     * 所以一合并，f[n] = 2*f[n-1]，初始条件f[0] = f[1] = 1
     * 并且
     * f[0] = f[1] = 1
     * f[2] = 2 = 2^1
     * f[3] = 4 = 2^2
     * f[4] = 8 = 2^3
     * ...
     * f[n] = 2^n-1
     */
    private static class HardSolution {

        public int jumpFloorII(int n) {
            if (n == 0 || n == 1) return 1;
            return 1 << (n - 1); // 口诀：左移乘2，右移除2
        }

    }

}
