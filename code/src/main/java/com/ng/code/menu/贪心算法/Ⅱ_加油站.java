package com.ng.code.menu.贪心算法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/gas-station/description/?envType=study-plan-v2&envId=top
 * -interview-150
 * 原题描述:
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，
 * 开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * 提示:
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 105
 * 0 <= gas[i], cost[i] <= 104
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_加油站 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        LogUtil.print(hardSolution.canCompleteCircuit(gas, cost));
    }

    private static class EasySolution {

    }

    private static class HardSolution {

        /**
         * 假如当前一段路无法走下去了，就该放弃
         * 换个新的起点了。这个起点最多只能到这里了，从这段路的任何地方重新开始都到达不了更远的地方了，
         * 因为放弃之前走的路都是帮你的，不然早就无法走下去了，因此
         * 起点只能选在下一站，错的不是你，是你的起点和你的路。
         * <p>
         * 这个问题的关键在于理解如何处理油量不足的情况。当遍历数组时，我们不是简单地累加每个加油站的净油量（gas[i] - cost[i]），而是关注在当前加油站之后能否继续行驶。
         * 假设我们有一个环形路线，从第0个加油站开始，我们希望找到一个起点，使得从这个起点开始，即使在某个点油量不足，也能通过其他加油站补充油量后继续前行，最终回到起点。
         * 1. 初始化`sum`为0，表示当前的总油量。
         * 2. 初始化`min`为正无穷大，`minIndex`为-1，用于记录首次油量不足的加油站索引和最小负值。
         * 3. 遍历数组：
         * - 对于每个加油站i，我们将当前加油站的净油量加到`sum`上。如果`sum
         * `变为负数，说明在到达当前加油站之前已经用光了油，但因为我们在遍历中才遇到这种情况，所以之前的加油站（即`minIndex`）是最后一个足够油量到达的站。
         * - 如果`sum`比之前遇到的最小负值还要小，更新`min`和`minIndex`。
         * 4. 遍历结束后，如果`sum`仍然是负数，说明没有足够的油完成整个循环，返回-1。
         * 5. 如果`sum`是非负数，说明从`minIndex +
         * 1`开始的剩余部分可以形成一个完整的循环。因为我们在`minIndex`处油量不足，但是从那里开始的其他加油站可以提供足够的油补足这个缺口，并且最终回到起点。
         * 通过这种方法，我们只需要遍历一次数组就能确定是否存在这样的循环路径。这是因为我们不仅关注总的净油量，还关注了第一次油量不足的位置，从而可以判断从那个位置之后是否有可能形成一个闭合的循环。
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < gas.length; i++) {
                sum = sum + gas[i] - cost[i];
                if (sum < min && sum < 0) {
                    min = sum;
                    minIndex = i;
                }
            }
            if (sum < 0) {
                return -1;
            }
            return (minIndex + 1) % gas.length;
        }
    }

}
