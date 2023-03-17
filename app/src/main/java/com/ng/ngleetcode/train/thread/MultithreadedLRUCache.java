package com.ng.ngleetcode.train.thread;

import java.util.HashMap;
import java.util.Set;

public class MultithreadedLRUCache {

    private static class Node {
        Integer key;
        Integer val;
        Node next;
        Node pre;

        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
            this.next = this.pre = null;
        }
    }

    private static class DoubleList {
        Node head, tail;

        public DoubleList() {
            this.head = new Node(null, null);
            this.tail = new Node(null, null);
            this.head.next = tail;
            this.tail.pre = head;
        }

        /**
         * 头插
         */
        public void insert(Node node) {
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        }

        /**
         * 删除当前node
         */
        public Node delete(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            return node;
        }

        /**
         * 删除尾部
         */
        public Node deleteLast() {
            if (head.next != tail) {
                return this.delete(tail.pre);
            }
            return null;
        }
    }

    private final int capacity;
    private volatile HashMap<Integer, Node> map;
    private DoubleList list;

    public MultithreadedLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoubleList();
    }

    public Integer get(Integer key) {
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            this.set(key, node.val);
            return node.val;
        }
        return null;
    }

    public synchronized void set(Integer key, Integer value) {
        Node curNode = new Node(key, value);
        if (this.map.containsKey(key)) {
            Node oldNode = this.map.get(key);
            this.list.delete(oldNode);
            this.map.put(key, curNode);
            this.list.insert(curNode);
        } else {
            if (this.map.size() >= this.capacity) {
                Node last = this.list.deleteLast();
                this.map.remove(last.key);
            }
            this.map.put(key, curNode);
            this.list.insert(curNode);
        }
    }

    public Set<Integer> keySet() {
        return this.map.keySet();
    }

    public int size() {
        return this.map.size();
    }

}
