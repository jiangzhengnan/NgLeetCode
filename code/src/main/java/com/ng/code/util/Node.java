package com.ng.code.util;

/**
 * @author : 
 * @creation : 2022/02/27
 * @description :
 */
/**
 * 首先实现链表节点
 */
public class Node {
    public int key, val;
    public Node prev, next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
