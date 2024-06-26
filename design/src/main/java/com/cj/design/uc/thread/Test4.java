package com.cj.design.uc.thread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : 
 * @creation : 2023/02/15
 * @description :
 */
public class Test4 {

    public class Node {
        Node pre;
        Node next;
        Integer value;

        public Node(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + value + "}";
        }
    }

    @NonNull
    private volatile HashMap<Integer, Node> map;
    @NonNull
    private final Node head;
    @NonNull
    private final Node tail;

    private final int mMaxSize;

    @NonNull
    private final ReentrantReadWriteLock mLock;

    @NonNull
    public HashMap<Integer, Node> getMap() {
        return map;
    }

    public Test4() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
        mMaxSize = 2;
        mLock = new ReentrantReadWriteLock();
    }

    public void put(int key, int value) {
        try {
            mLock.writeLock().lock();
            String msg = "start write i:" + key + " value:" + value + " now:" + map + " size:" + map.size();
            removeTailNode();
            Node node;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.value = value;
            } else {
                node = new Node(key);
                map.put(key, node);
            }
            moveToHead(node);
            print(msg + "      result: " + map);
        } finally {
            mLock.writeLock().unlock();
        }
    }

    public Integer get(int key) {
        Node node;
        if (map.containsKey(key) && map.get(key) != null) {
            node = map.get(key);
            moveToHead(node);
        } else {
            return -1;
        }
        return node == null ? -1 : node.value;
    }

    private void moveToHead(@Nullable Node node) {
        if (node == null) {
            return;
        }
        Node nodePre = node.pre;
        Node nodeNext = node.next;
        if (nodePre != null && nodeNext != null) {
            nodePre.next = nodeNext;
            nodeNext.pre = nodePre;
        }
        Node headNext = head.next;
        head.next = node;
        node.pre = head;
        node.next = headNext;
        headNext.pre = node;
    }

    private void removeTailNode() {
        if (map.size() + 1 <= mMaxSize) {
            return;
        }
        Node rk = tail.pre;
        if (tail.pre.pre == null) {
            return;
        }
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        System.out.println("remove:" + rk.value);
        map.remove(rk.value);

    }

    private static void print(String msg) {
        System.out.println(System.currentTimeMillis() + "-" + Thread.currentThread().getName() + ":  " + msg + "  " + (System.currentTimeMillis() - mStartTime));
    }


    private static long mStartTime;
    private static AtomicInteger mIndex;

    //用 Java 实现一个并发安全的 LRU，读写操作都要有。性能要尽可能得高。
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        mIndex = new AtomicInteger(0);
        mStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 2000; j++) {
                        int value = mIndex.addAndGet(1);
                        test4.put(value, value);
                        System.out.println("read " + test4.get(value) + "  " + (System.currentTimeMillis() - mStartTime));
                    }


                }
            }).start();
        }

    }

}
