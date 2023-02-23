package com.ng.train.thread;

import com.ng.train.LogUtil;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/15
 * @description :
 * ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
 */
public class Test3 {
    private static long mStartTime;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public int mConfig;

    public void readConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.print(Thread.currentThread().getName() + " 读取start[" + mConfig + " " + getCostTime());
                lock.readLock().lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.readLock().unlock();
                LogUtil.print(Thread.currentThread().getName() + "    读取end]" + mConfig + " " + getCostTime());
            }
        }).start();

    }

    public void writeConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.print(Thread.currentThread().getName() + " 写入start [" + mConfig + " " + getCostTime());
                lock.writeLock().lock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mConfig++;
                lock.writeLock().unlock();
                LogUtil.print(Thread.currentThread().getName() + "    写入end ]" + mConfig + " " + getCostTime());
            }
        }).start();

    }

    public void testConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                LogUtil.print(Thread.currentThread().getName() + " 读取start[" + mConfig + " " + getCostTime());
                lock.readLock().lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.print(Thread.currentThread().getName() + "    读取end]" + mConfig + " " + getCostTime());
                lock.readLock().unlock();

                LogUtil.print(Thread.currentThread().getName() + " 写入start [" + mConfig + " " + getCostTime());
                lock.writeLock().lock();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mConfig++;
                lock.writeLock().unlock();


                LogUtil.print(Thread.currentThread().getName() + "    写入end ]" + mConfig + " " + getCostTime());
            }
        }).start();

    }

    public static long getCostTime() {
        return System.currentTimeMillis() - mStartTime;
    }

    public static void main(String[] args) {
        mStartTime = System.currentTimeMillis();
        Test3 test = new Test3();
//        test.readConfig();
//        test.writeConfig();
        test.testConfig();

    }

}
