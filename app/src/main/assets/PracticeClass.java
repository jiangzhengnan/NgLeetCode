package com.ng.code.menu;

import androidx.annotation.NonNull;

import com.ng.code.util.Node;
import com.ng.code.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/03/18
 * @description :
 */
public class PracticeClass {

    public static void main(String[] args) {
        int[][] op = new int[][]{
                {1, 1, 1},
                {1, 2, 2},
                {2, 1},
                {1, 3, 3},
                {2, 2},
                {1, 4, 4},
                {2, 1},
                {2, 3},
                {2, 4}
        };
        LogUtil.pring(LRU(op, 2));
    }

    //链表+hash查找
    public static int[] LRU(int[][] operators, int k) {
        maxSize = k;
        nodeHead.next = nodeTail;
        List<Integer> result = new ArrayList<>();
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                //set
                set(operator[1], operator[2]);
            } else {
                //get
                result.add(get(operator[1]));
            }
        }
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    static int maxSize;
    static Node nodeHead = new Node(-1, -1);
    static Node nodeTail = new Node(-1, -1);

    static Map<Integer, Node> map = new HashMap<>();

    private static int get(int key) {
//        if (map.containsKey(key)) {
//            moveToHead(map.get(key));
//            return map.get(key).val;
//        }
//        return -1;
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    private static void set(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).val = value;
            moveToHead(map.get(key));
        } else {
            Node newNode = new Node(key, value);
            if (map.size() < maxSize) {
                moveToHead(newNode);
                map.put(key, newNode);
            } else {
                map.remove(removeTail());
                moveToHead(newNode);
                map.put(key, newNode);
            }

        }
    }

    //移除尾部
    private static int removeTail() {
        int key = nodeTail.prev.key;
        nodeTail.prev.prev.next = nodeTail;
        nodeTail.prev = nodeTail.prev.prev;
        return key;
    }

    //移动到链表头部
    private static void moveToHead(Node node) {
        node.next = nodeHead.next;
        nodeHead.next.prev = node;
        nodeHead.next = node;
        node.prev = nodeHead;
    }


}
