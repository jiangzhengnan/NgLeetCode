package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ng.base.utils.LogUtil;


public class NewThreadScheduler implements Scheduler {
  ExecutorService service;

  public NewThreadScheduler() {
    service = Executors.newCachedThreadPool(new RxThreadFactory("-thread-new-"));
  }

  @Override
  public void schedule(Runnable runnable) {
    LogUtil.print("NewThreadScheduler - schedule");
    service.submit(runnable);
  }
}