package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ng.base.utils.LogUtil;


public class IOScheduler implements Scheduler {
  ExecutorService service;

  public IOScheduler() {
    // RxThreadFactory 线程工程
    service = Executors.newCachedThreadPool(new RxThreadFactory("-thread-io-"));
  }

  @Override
  public void schedule(Runnable runnable) {
    LogUtil.print("IOScheduler - schedule");
    service.submit(runnable);
  }
}