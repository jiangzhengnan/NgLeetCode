package com.ng.code.work.train.thread;

import androidx.annotation.NonNull;

import com.ng.base.utils.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : 
 * @creation : 2023/02/20
 * @description :
 */
public class AdHelper锁 {
    private static volatile List<AdnInitCallback> sCallbacks = new ArrayList<>();
    private static final ReentrantLock sInitLock = new ReentrantLock();

    private static final ReentrantReadWriteLock sInitLockNew = new ReentrantReadWriteLock();

    private static long startTime = 0;

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        AdHelper锁 practiceClass = new AdHelper锁();
        practiceClass.test();
        practiceClass.init();
    }

    private void init() {
        for (int i = 0; i < 500; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    randomSleep();

                    LogUtil.print(Thread.currentThread()
                                        .getName() + " init run :" + sCallbacks.size());

                    //sInitLock.lock();
                    sInitLockNew.readLock().lock();

                    List<AdnInitCallback> callbacksCopy = new ArrayList<>(sCallbacks);

                    //sInitLock.unlock();
                    sInitLockNew.readLock().unlock();

                    LogUtil.print(callbacksCopy.size() + " ");

                    Iterator<AdnInitCallback> it = callbacksCopy.iterator();
                    int index = 0;
                    while (it.hasNext()) {
                        AdnInitCallback callback = it.next();
                        if (callback != null) {
                            LogUtil.print(index + " do");
                            callback.success();
                        } else {
                            LogUtil.print(index + " null");
                        }
                        index++;
                        it.remove();
                    }
                }
            }).start();
        }
    }

    private void randomSleep() {
        try {
            long time = new Random().nextInt(10) + 1;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void test() {

        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    randomSleep();
                    checkInit(new AdnInitCallback() {

                        @Override
                        public void success() {
                            LogUtil.print(Thread.currentThread()
                                                .getName() + " success" + (System.currentTimeMillis() - startTime));
                        }

                        @Override
                        public void error(final int code, final String msg) {

                        }
                    });
                    LogUtil.print(Thread.currentThread()
                                        .getName() + " test run :" + sCallbacks.size());

                }
            }).start();
        }
    }

    public void checkInit(@NonNull final AdnInitCallback callback) {
        //sInitLock.lock();
        sInitLockNew.writeLock().lock();

        sCallbacks.add(callback);

        //sInitLock.unlock();
        sInitLockNew.writeLock().unlock();

    }

    interface AdnInitCallback {
        void success();

        void error(int code, String msg);
    }
}
