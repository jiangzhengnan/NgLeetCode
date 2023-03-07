package com.ng.train.thread;


import java.util.concurrent.locks.ReentrantLock;

import com.ng.base.LogUtil;

/**
 * @author : 
 * @creation : 2023/02/15
 * @description :
 * ReentrantLock lock = new ReentrantLock();
 */
public class Test2 {
    private static long mStartTime;
    private ReentrantLock lock = new ReentrantLock();
    public int mConfig;

    public void readConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.print(Thread.currentThread().getName() + " 读取配置:" + mConfig + " " + getCostTime());
                lock.unlock();
            }
        }).start();

    }

    public void writeConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mConfig++;
                LogUtil.print(Thread.currentThread().getName() + " 写入配置:" + mConfig + " " + getCostTime());
                lock.unlock();
            }
        }).start();

    }

    public static long getCostTime() {
        return System.currentTimeMillis() - mStartTime;
    }

    public static void main(String[] args) {
        mStartTime = System.currentTimeMillis();
        Test2 test2 = new Test2();
        test2.writeConfig();
        test2.readConfig();
        test2.writeConfig();
        test2.readConfig();
    }

}
