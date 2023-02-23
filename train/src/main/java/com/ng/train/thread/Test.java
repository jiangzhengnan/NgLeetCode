package com.ng.train.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/15
 * @description :
 * ReentrantLock lock = new ReentrantLock();
 * ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
 */
public class Test {

    static class TestRunnable implements Runnable {

        private ReentrantLock reentrantLock;

        public TestRunnable(ReentrantLock reentrantLock) {
            this.reentrantLock = reentrantLock;
        }

        @Override
        public void run() {
            while (true) {
                reentrantLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock(true);

        TestRunnable reentrantLockDemo01 = new TestRunnable(reentrantLock);

        Thread thread1 = new Thread(reentrantLockDemo01);
        Thread thread2 = new Thread(reentrantLockDemo01);

        thread1.start();
        thread2.start();

    }

}
