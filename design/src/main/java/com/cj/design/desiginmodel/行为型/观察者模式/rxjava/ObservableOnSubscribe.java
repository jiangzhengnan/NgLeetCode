package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;


import androidx.annotation.NonNull;

public interface ObservableOnSubscribe<T> {

  void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception;
}
