package com.ng.code.menu.多线程;

import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/04/19
 * @description :
 * 算法题：两个线程分别持续打印奇数和偶数，实现两个线程的交替打印（从小到大）
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 两个线程交替打印 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new PrintOdd(counter)).start();
        new Thread(new PrintEven(counter)).start();
    }

    static class PrintOdd implements Runnable {
        public Counter counter;

        public PrintOdd(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (counter.value <= 100) {
                synchronized (counter) {
                    if (counter.odd) {
                        System.out.println(Thread.currentThread().getName() + ":" + counter.value);
                        counter.value++;
                        counter.odd = !counter.odd;
                        counter.notify();
                    }
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class PrintEven implements Runnable {
        public Counter counter;

        public PrintEven(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (counter.value <= 100) {
                synchronized (counter) {
                    if (!counter.odd) {
                        System.out.println(Thread.currentThread().getName() + ":" + counter.value);
                        counter.value++;
                        counter.odd = !counter.odd;
                        counter.notify();
                    }
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Counter {
        public int value = 1;
        public boolean odd = true;
    }
}
