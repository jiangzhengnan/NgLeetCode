package com.ng.code.menu.多线程;

import java.util.LinkedList;

import com.ng.code.util.Solution;

/**
 * @author :
 * @creation : 2022/04/19
 * @description :
 * 需要参考
 * https://blog.csdn.net/skz980619/article/details/119703042
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅰ_生产者消费者模式 {

    /**
     * 此示例包括两个线程，一个是生产者，一个是消费者。
     * 生产者在共享队列中添加元素，当队列达到最大容量时，它将等待消费者消耗元素。
     * 消费者从共享队列中删除元素，当队列为空时，它将等待生产者生产元素。
     * 所有生产者和消费者都共享同一个共享队列，并在修改队列时使用synchronized关键字确保线程安全性。
     * 为了避免不必要的等待，wait()和notifyAll()方法在共享队列中使用。
     */
    public static void main(String[] args) {
        final int MAX_CAPACITY = 5;
        LinkedList<Integer> sharedQueue = new LinkedList<Integer>();

        Thread producer = new Thread(new Producer(sharedQueue, MAX_CAPACITY), "Producer");
        Thread consumer = new Thread(new Consumer(sharedQueue), "Consumer");

        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {

    private final LinkedList<Integer> sharedQueue;
    private final int MAX_CAPACITY;

    public Producer(LinkedList<Integer> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.MAX_CAPACITY = size;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (sharedQueue) {
                while (sharedQueue.size() == MAX_CAPACITY) {
                    try {
                        System.out.println("Queue is full, waiting for consumer to consume...");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Producing value: " + value);
                sharedQueue.add(value++);
                sharedQueue.notifyAll();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final LinkedList<Integer> sharedQueue;

    public Consumer(LinkedList<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQueue) {
                while (sharedQueue.isEmpty()) {
                    try {
                        System.out.println("Queue is empty, waiting for producer to produce...");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = sharedQueue.removeFirst();
                System.out.println("Consuming value: " + value);
                sharedQueue.notifyAll();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
