package com.ng.code.menu.多线程;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * <p>
 * 示例:
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅰ_手写死锁 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        //easySolution.lock();
        hardSolution.lock();
    }

    /**
     * 构建一个死锁
     */
    private static class EasySolution {
        private void lock() {
            Object lock1 = new Object();
            Object lock2 = new Object();
            Thread thread1 = new Thread(() -> {
                synchronized (lock1) {
                    System.out.println("A获取到了锁A");
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("A获取到了锁B");
                    }
                }
            });
            Thread thread2 = new Thread(() -> {
                synchronized (lock2) {
                    System.out.println("B获取到了锁B");
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("B获取到了锁A");
                    }
                }
            });

            thread1.start();
            thread2.start();
        }
    }

    /**
     * 避免死锁
     * 使用定时锁：使用ReentrantLock的tryLock()方法尝试获取锁，并设置一个超时时间。
     * 如果无法在超时时间内获取锁，线程可以释放已持有的锁并重试。
     */
    private static class HardSolution {

        private void lock() {
            Lock lock1 = new ReentrantLock();
            Lock lock2 = new ReentrantLock();
            Thread thread1 = new Thread(() -> {
                try {
                    LogUtil.print("A获取到了锁A");
                    lock1.lock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LogUtil.print("A尝试获取锁B");

                    if (lock2.tryLock()) {
                        try {
                            LogUtil.print("A获取到了锁B");
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        LogUtil.print("A获取不到锁B，释放锁A");
                    }
                } finally {
                    lock1.unlock();
                }
            });
            Thread thread2 = new Thread(() -> {
                try {
                    LogUtil.print("B获取到了锁B");
                    lock2.lock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LogUtil.print("B尝试获取锁A");

                    if (lock1.tryLock()) {
                        try {
                            LogUtil.print("B获取到了锁A");
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        LogUtil.print("B获取不到锁A，释放锁B");
                    }
                } finally {
                    lock2.unlock();
                }
            });
            thread1.start();
            thread2.start();
        }
    }

}
