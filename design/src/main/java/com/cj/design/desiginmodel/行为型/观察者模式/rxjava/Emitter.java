package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;

public interface Emitter<T> {

  void onNext(T value);

  void onError(Throwable e);

  void onComplete();
}
