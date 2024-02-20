package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;



public final class ObservableCreate<T> extends Observable<T> {
  //source 为create 中创建的ObservableOnSubscribe对象
  final ObservableOnSubscribe<T> source;

  public ObservableCreate(ObservableOnSubscribe<T> source) {
    this.source = source;
  }

  @Override
  protected void subscribeActual(Observer<? super T> observer) {
    //传入的observer为被订阅的观察者
    CreateEmitter<T> emitter = new CreateEmitter<T>(observer);
    //通知观察者被订阅，
    observer.onSubscribe();
    try {
      //emitter开始执行，其发出的事件会传递到observer
      //RLog.printInfo("emitter开始发送事件");
      source.subscribe(emitter);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 把Emitter发出的事件分发给observer
   *
   * @param <T>
   */
  static final class CreateEmitter<T> implements ObservableEmitter<T> {

    final Observer<? super T> observer;

    CreateEmitter(Observer<? super T> observer) {
      this.observer = observer;
    }

    @Override
    public void onNext(T t) {
      //CheckUtils.checkNotNull(t, "onNext called parameter can not be null");
      observer.onNext(t);
    }

    @Override
    public void onError(Throwable error) {
      observer.onError(error);
    }

    @Override
    public void onComplete() {
      observer.onComplete();
    }

  }
}