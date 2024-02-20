package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainScheduler implements Scheduler {
  private Handler mMainHandler;

  public MainScheduler() {
    mMainHandler = new Handler(Looper.getMainLooper());
  }

  @Override
  public void schedule(Runnable runnable) {
    ScheduledRunnable scheduled = new ScheduledRunnable(runnable);
    Message message = Message.obtain(mMainHandler, scheduled);
    mMainHandler.sendMessageDelayed(message, 0);
  }

  private static final class ScheduledRunnable implements Runnable {
    private final Runnable delegate;

    ScheduledRunnable(Runnable delegate) {
      this.delegate = delegate;
    }

    @Override
    public void run() {
      delegate.run();
    }
  }
}
