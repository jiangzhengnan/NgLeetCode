package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread;


import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observable;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observer;

/**
 * 每次调用observeOn，都会切换一下线程。
 * 这个比较好理解，因为每次调用都会影响后面观察者运行的线程，
 * 线程改变后，会在新的线程中将数据发送给的Observer。
 */
public class ObservableObserveOn<T> extends Observable<T> {

  Observable<T> source;
  Scheduler scheduler;

  public ObservableObserveOn(Observable<T> source, Scheduler scheduler) {
    this.source = source;
    this.scheduler = scheduler;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {
    source.subscribe(new ObserveOnObserver<T>(scheduler, observer));
  }

  static final class ObserveOnObserver<T> implements Observer<T>, Runnable {

    Scheduler scheduler;
    //下一个流
    Observer<? super T> downStream;
    T value;

    public ObserveOnObserver(Scheduler scheduler, Observer<? super T> observer) {
      this.scheduler = scheduler;
      this.downStream = observer;
    }

    @Override
    public void onSubscribe() {
      downStream.onSubscribe();
    }

    @Override
    public void onNext(T t) {
      value = t;
      // 通过调度器切换线程。
      scheduler.schedule(this);
    }


    @Override
    public void onError(Throwable t) {
      downStream.onError(t);
    }

    @Override
    public void onComplete() {
      downStream.onComplete();
    }

    @Override
    public void run() {
      downStream.onNext(value);
    }
  }
}

