package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;

import androidx.annotation.NonNull;

import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.flatmap.ObservableFlapMapArray;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.flatmap.ObservableFlatMapIterable;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.map.Function;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.map.ObservableMap;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread.ObservableObserveOn;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread.ObservableSubscribeOn;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread.Scheduler;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.zip.BiFunction;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.zip.ObservableZip;


/**
 * 被观察者
 * 被观察者，使用RxJava时需要创建一个被观察者，它会决定什么时候触发事件以及触发怎样的事件。
 * 有点类似在上游发送命令，并且可以指定同异步或者操作模块的顺序与次数。
 */
public abstract class Observable<T> implements ObservableSource<T> {

  public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
    return new ObservableCreate<T>(onSubscribe);
  }

  public final Observable<T> observeOn(Scheduler scheduler) {
    return new ObservableObserveOn<T>(this, scheduler);
  }

  public final Observable<T> subscribeOn(Scheduler scheduler) {
    //ObjectHelper.requireNonNull(mapper, "mapper is null");
    return new ObservableSubscribeOn<T>(this, scheduler);
  }

  public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
    //ObjectHelper.requireNonNull(mapper, "mapper is null");
    return new ObservableMap<T, R>(this, mapper);
  }

  public final <R> Observable<R> flatMap(Function<? super T, Iterable<R>> mapper) {
    //ObjectHelper.requireNonNull(mapper, "mapper is null");
    return new ObservableFlatMapIterable<>(this, mapper);
  }

  public final <R> Observable<R> flatArray(Function<? super T, R[]> mapper) {
    //ObjectHelper.requireNonNull(mapper, "mapper is null");
    return new ObservableFlapMapArray<>(this, mapper);
  }

  public static <T, R> Observable<R> zip(
      ObservableSource<? extends T> source1,
      ObservableSource<? extends T> source2,
      BiFunction<? super T, ? super T, ? extends R> zipper) {
    //ObjectHelper.requireNonNull(mapper, "mapper is null");
    return new ObservableZip<>(new ObservableSource[]{source1, source2}, zipper);
  }


  //用于外部调用
  @Override
  public void subscribe(@NonNull Observer<? super T> observer) {
    subscribeActual(observer);
  }

  // 用于子类实现
  protected abstract void subscribeActual(Observer<? super T> observer);

}
