package com.ng.ngleetcode.train.rtb;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发执行器，在某一个线程中插入一个并发过程
 */
public class ConcurrentExecutor {


    public static void main(String[] args) {

    }

    private static final String TAG = "ConcurrentExecutor";

    private static final int CORE_POOL_CORE_SIZE = 5;
    private static final int CORE_POOL_MAX_SIZE = Math.max(2 + 2, CORE_POOL_CORE_SIZE);
    private static final int CORE_THREAD_KEEP_ALIVE_TIME = 20000;    // 20秒
    private static final ThreadFactory CORE_THREAD_FACTORY = new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(Looper.getMainLooper().getThread().getPriority());
            thread.setName("noah-concurrent-" + thread.getId());
            return thread;
        }
    };

    private static final ThreadPoolExecutor mCoreThreadPool = new ThreadPoolExecutor(
            CORE_POOL_CORE_SIZE, CORE_POOL_MAX_SIZE, CORE_THREAD_KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), CORE_THREAD_FACTORY);
    static{
        try {
            mCoreThreadPool.allowCoreThreadTimeOut(true);
            mCoreThreadPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    try {
                        throw new RuntimeException("Core Thread Reject Execution");
                    } catch (RuntimeException e) {
                        //NHLogger.sendException(e);
                    }
                }
            });
        } catch (Throwable t) {
            //NHLogger.sendException(t);
        }
    }

    public static void execute(Runnable runnable) {
        mCoreThreadPool.execute(runnable);
    }

    private long startTime = -1;
    private final long mTimeout;
    private final Runnable mRunnable;
    private final LinkedBlockingDeque<Runnable> mCallbacks;
    private final AtomicInteger mRunningCnt;
    private Handler mOrgThreadHandler;
    private boolean mHasExited;

    public ConcurrentExecutor(long timeout) {
        mTimeout = timeout;
        mCallbacks = new LinkedBlockingDeque<>();
        mRunningCnt = new AtomicInteger();
        Looper orgLooper = Looper.myLooper();
        if (orgLooper != null) {
            mOrgThreadHandler = new Handler(orgLooper);
        }
        mRunnable = new Runnable() {
            @Override
            public void run() {
                doRun();
            }
        };
    }

    public void execute(@NonNull final List<Runnable> runnableList) {
        Log.d("xigua","先执行runnableList");
        for (final Runnable runnable : runnableList) {
            // 并发
            Runnable concurrentRunnable = new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                    mRunningCnt.decrementAndGet();
                }
            };
            mRunningCnt.incrementAndGet();
            mCoreThreadPool.execute(concurrentRunnable);
        }

        Log.d("xigua","再执行callbackList : " +startTime +" " + mCallbacks.size());

        // 当前线程进入列队循环
        if (startTime < 0) {
            startTime = SystemClock.uptimeMillis();
            //RunLog.i(TAG, "Concurrent execute %d tasks", runnableList.size());
            mRunnable.run();
        }
    }

    public boolean callback(Runnable runnable) {
        Log.d("xigua","callback, mHasExited:" + mHasExited +"   isTimeout():" + isTimeout());

        if (mHasExited || isTimeout()) {
            // 执行窗口已退出或执行超时，callback只能放到线程消息队列里去，如果对应线程没有消息队列就返回false, 由外层自己处理
            if (mOrgThreadHandler != null) {
                //RunLog.w(TAG, "Concurrent execute exit or timeout, callback will be execute after this callback");
                Log.d("xigua","post 到主线程前面");
                mOrgThreadHandler.postAtFrontOfQueue(runnable);
                return true;
            }
            return false;
        } else {
            Log.d("xigua","post到callback列表");
            mCallbacks.offer(runnable);
        }
        return true;
    }

    public boolean isTimeout() {
        return (SystemClock.uptimeMillis() - startTime) >= mTimeout;
    }

    public boolean isCallbackEmpty() {
            return mCallbacks.isEmpty();
    }

    private void doRun() {

        // 最开始的时候可以认为callback队列是空的
        while ((!isTimeout() && mRunningCnt.get() > 0) || !isCallbackEmpty()) {
            try {
                Runnable runnable;
                long cost = SystemClock.uptimeMillis() - startTime;
                if (cost < mTimeout) {
                    runnable = mCallbacks.poll(mTimeout - cost, TimeUnit.MILLISECONDS);
                } else {
                    // 把队列里的runnable执行完
                    runnable = mCallbacks.poll();
                }

                if (runnable != null) {
                    Log.d("xigua","doRun item");

                    runnable.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mHasExited = true;
        Log.d("xigua","doRun：" +mHasExited);

        // 最后清一次callback队列，上方没有保障线程互斥，有可能造成callback被忽略
        int count = 0;
        while (!isCallbackEmpty() && count++ < CORE_POOL_CORE_SIZE) {
            Runnable runnable;
                runnable = mCallbacks.poll();

            if (runnable != null) {
                Log.d("xigua","doRun item2");

                runnable.run();
            }
        }

       //RunLog.i(TAG, "Concurrent execute exit, runningCallbackCnt = %d, isTimeout = %s, isCallbackEmpty = %s, cost = %d",
       //        mRunningCnt.get(), isTimeout(), isCallbackEmpty(), (SystemClock.uptimeMillis() - startTime));
    }
}
