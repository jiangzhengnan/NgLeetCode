package com.ng.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ng.base.LogUtil;

public class PracticeClass {
    private static volatile List<AdnInitCallback> sCallbacks = new ArrayList<>();
    private static final ReentrantLock sInitLock = new ReentrantLock();

    public static void main(String[] args) {
        PracticeClass practiceClass = new PracticeClass();
        practiceClass.test();
        practiceClass.init();
    }

    private void init() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                randomSleep();

                LogUtil.pring(Thread.currentThread().getName() + " init run :" + sCallbacks.size());
                sInitLock.lock();
                List<AdnInitCallback> callbacksCopy = new ArrayList<>(sCallbacks);
                sInitLock.unlock();
                LogUtil.pring(callbacksCopy.size() + " ");

                Iterator<AdnInitCallback> it = callbacksCopy.iterator();
                int index = 0;
                while (it.hasNext()) {
                    AdnInitCallback callback = it.next();
                    if (callback != null) {
                        LogUtil.pring(index + " do");
                        callback.success();
                    } else {
                        LogUtil.pring(index + " null");
                    }
                    index++;
                    it.remove();
                }
            }
        }).start();
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

        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    randomSleep();
                    checkInit(new AdnInitCallback() {

                        @Override
                        public void success() {
                            LogUtil.pring(Thread.currentThread().getName() + " success");
                        }

                        @Override
                        public void error(final int code, final String msg) {

                        }
                    });
                    LogUtil.pring(Thread.currentThread()
                                        .getName() + " test run :" + sCallbacks.size());

                }
            }).start();
        }
    }

    public void checkInit(@NonNull final AdnInitCallback callback) {
        sInitLock.lock();
        sCallbacks.add(callback);
        sInitLock.unlock();
    }

    interface AdnInitCallback {
        void success();

        void error(int code, String msg);
    }
}



