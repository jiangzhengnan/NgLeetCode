package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread;


import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observable;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observer;

/**
 * 当多次调用subscribeOn()时，只有第一个subscribeOn() 起作用。
 * 上面的例子中，连续3次调用
 * subscribeOn(Schedulers.NEW_THREAD)
 * subscribeOn(Schedulers.IO)
 * subscribeOn(Schedulers.NEW_THREAD)
 *
 * 其实线程也是切换了三次，只不过最后一次切换成了第一个subscribeOn()指定的线程，所以只有第一个真正起到了作用。
 */
public class ObservableSubscribeOn<T> extends Observable<T> {

  Observable<T> source;
  Scheduler scheduler;

  public ObservableSubscribeOn(Observable<T> source, Scheduler scheduler) {
    this.source = source;
    this.scheduler = scheduler;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {
    SubscribeOnObserver parent = new SubscribeOnObserver<>(observer);

    observer.onSubscribe();

    // 将Obserable.subscribe()操作放到Runnable中执行。
    scheduler.schedule(new SubscribeTask(parent));
  }

  static class SubscribeOnObserver<T> implements Observer<T> {

    Observer downStream;

    public SubscribeOnObserver(Observer<? super T> observer) {
      this.downStream = observer;
    }

    @Override
    public void onSubscribe() {
    }

    @Override
    public void onNext(T t) {
      downStream.onNext(t);
    }


    @Override
    public void onError(Throwable t) {
      downStream.onError(t);
    }

    @Override
    public void onComplete() {
      downStream.onComplete();
    }
  }


  final class SubscribeTask implements Runnable {

    private final SubscribeOnObserver<T> parent;

    SubscribeTask(SubscribeOnObserver<T> parent) {
      this.parent = parent;
    }

    @Override
    public void run() {
      source.subscribe(parent);
    }
  }
}

