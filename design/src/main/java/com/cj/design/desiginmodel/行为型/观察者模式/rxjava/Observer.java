package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;

/**
 * 观察者
 * 可以在未来某个时刻响应Observable的通知，它可以在不同的线程中执行任务。
 */
public interface Observer<T> {

  void onSubscribe();

  void onNext(T value);

  void onError(Throwable e);

  void onComplete();

}
