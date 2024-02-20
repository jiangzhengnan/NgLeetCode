package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;


import androidx.annotation.NonNull;

public interface ObservableSource<T> {

  /**
   * Subscribes the given Observer to this ObservableSource instance.
   * @param observer the Observer, not null
   * @throws NullPointerException if {@code observer} is null
   */
  void subscribe(@NonNull Observer<? super T> observer);
}
