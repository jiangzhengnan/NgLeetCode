package com.ng.code.menu.模拟;

import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 一个缓存结构需要实现如下功能。
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * 但是缓存结构中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，
 * 然后才能把新记录加入。这个策略为：在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录；
 * 如果调用次数最少的key有多个，上次调用发生最早的key被删除
 * 这就是LFU缓存替换算法。实现这个结构，K作为参数给出
 *
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 *
 * 对于每个操作2，返回一个答案
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,1,1],[1,2,2],[1,3,2],[1,2,4],[1,3,5],[2,2],[1,4,4],[2,1]],3
 * 返回值：
 * [4,-1]
 * 说明：
 * 在执行"1 4 4"后，"1 1 1"被删除。因此第二次询问的答案为-1
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_设计LFU缓存结构 {

    public static void main(String[] args) {

    }

    /**
     * 双hash表
     * 首先定义两个Map，分别记为cache和freqMap。cache用于存储缓存，键为缓存的键，值为key、value组成的Node，freqMap用于存储频次，
     * 键为某个Node调用次数，值为对应频次的双向链表。然后定义一个变量min记录最小调用次数。
     * 对于get方法，如果cache不包含对应的key，直接返回-1。如果包含，则找到对应的node，更新其调用的频次。并返回node对应的value。
     * 对于set方法，如果cache不包含对应的key，并且容量达到上限容量，这时候要考虑删除一条记录。首先根据最小调用次数找到对应的双向链表，
     * 然后删掉双向链表的第一个node，同时从cache中移除掉对应的key。如果cache包含对应的key，则新建一个node，将其放入cache缓存，
     * 并在频次为1的双向链表添加该node。
     */
    private static class EasySolution {
        /**
         * lfu design
         *
         * @param operators int整型二维数组 ops
         * @param k         int整型 the k
         * @return int整型一维数组
         */
        public int[] LFU(int[][] operators, int k) {
            //记录get调用的次数
            int cnt = 0;
            for (int[] opera : operators) {
                if (opera[0] == 2) {
                    cnt++;
                }
            }
            //根据cnt新建结果数组
            int[] res = new int[cnt];
            int id = 0;
            //新建缓存结构
            LFUCache lfu = new LFUCache(k);
            for (int[] opera : operators) {
                //执行set操作
                if (opera[0] == 1) {
                    lfu.set(opera[1], opera[2]);
                }
                //执行get操作
                else {
                    int value = lfu.get(opera[1]);
                    res[id++] = value;
                }
            }
            return res;
        }


    }

    static class LFUCache {
        //定义Node结构，包含一个key和一个value，并初始化调用次数freq为1
        class Node {
            int key;
            int value;
            int freq = 1;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //用于存储缓存，键为缓存的键，值为key、value组成的Node
        Map<Integer, Node> cache;
        //用于存储频次，键为某个Node调用次数，值为对应频次的双向链表
        Map<Integer, LinkedHashSet<Node>> freqMap;
        //缓存的容量
        int capacity;
        //记录最小调用次数
        int min;

        //初始化
        public LFUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(capacity);
            freqMap = new HashMap<>();
        }

        //返回key对应的value值
        public int get(int key) {
            //如果缓存中不存在key，直接返回-1
            if (!cache.containsKey(key)) {
                return -1;
            }
            //找到对应的node
            Node node = cache.get(key);
            //更新调用频次
            freqInc(node);
            return node.value;
        }

        //将记录(key,value)插入缓存结构
        public void set(int key, int value) {
            //如果缓存中已经存在这个key
            if (cache.containsKey(key)) {
                //找到对应的node，设置值为value，更新频次
                Node node = cache.get(key);
                node.value = value;
                freqInc(node);
            }
            //如果缓存中不存在这个key
            else {
                //如果缓存的大小达到了容量capacity
                if (cache.size() == capacity) {
                    //根据策略从双向链表删掉对应的节点
                    Node deadNode = removeNode();
                    //从缓存中移除对应的key
                    cache.remove(deadNode.key);
                }
                //新建一个Node，将其放入缓存，并在双向链表添加该Node
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
            }

        }

        //更新频次
        public void freqInc(Node node) {
            //找到对应的双向链表，移除掉node
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            set.remove(node);
            //如果删掉的刚好是最小频次，并且只存在一个这样的node，需要更新min
            if (freq == min && set.size() == 0) {
                min = freq + 1;
            }
            //node对应的频次加1
            node.freq++;
            //找到node新的频次对应的双向链表
            LinkedHashSet<Node> newSet = freqMap.get(node.freq);
            if (newSet == null) {
                //如果为空，新建一个，并放到缓存
                newSet = new LinkedHashSet<>();
                freqMap.put(node.freq, newSet);
            }
            //在新的频次对应的双向链表中添加node
            newSet.add(node);
        }

        //根据策略从双向链表删掉对应的节点
        public Node removeNode() {
            //找到最小频次对应的双向链表
            LinkedHashSet<Node> set = freqMap.get(min);
            //找到该链表中最早进来的node
            Node deadNode = set.iterator().next();
            //移除掉这个node
            set.remove(deadNode);
            return deadNode;
        }

        //在频次为1的链表中添加新的node
        public void addNode(Node node) {
            //找到频次为1的链表
            LinkedHashSet<Node> set = freqMap.get(1);
            if (set == null) {
                //如果为空，新建一个并放入频次map
                set = new LinkedHashSet<>();
                freqMap.put(1, set);
            }
            //添加node，并设置min为1
            set.add(node);
            min = 1;
        }

    }

    private static class HardSolution {

    }

}
