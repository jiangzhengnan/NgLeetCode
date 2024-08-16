package com.ng.code;
import java.util.concurrent.atomic.AtomicInteger;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.BasePracticeClass;

public class PracticeClass extends BasePracticeClass {
    private int num = 0;

    @Override
    public void run() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(2, 3);

        for (int i = 0; i < 10; i++) {
            Thread testThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    num++;
                    LogUtil.print(num + "  " + Thread.currentThread().getName());
                }
            });
            testThread.start();
            try {
                testThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
