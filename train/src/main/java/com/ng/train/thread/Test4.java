package com.ng.train.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/15
 * @description :
 * ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
 */
public class Test4 {
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static int test = 2;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 3; i++) {
            threadPool.execute(() -> {
                try {
                    readWriteLock.writeLock().lockInterruptibly();
                    System.out.println("写入数据" + (test++));
                    // 假装耗时操作
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            });
        }
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    readWriteLock.readLock().lockInterruptibly();
                    System.out.println("读取数据" + test);
                    // 假装耗时操作
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readWriteLock.readLock().unlock();
                }
            });
        }
    }


}
