package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.flatmap;



import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observable;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.ObservableSource;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.Observer;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.map.Function;
import com.ng.base.utils.LogUtil;

//T表示输入值，U表示输出值，把 T转换成 U。
public class ObservableFlapMapArray<T, U> extends Observable<U> {

  final Function<? super T, U[]> function;
  /**
   * source为create中创建的ObservableOnSubscribe对象
   */
  final ObservableSource<T> source;

  public ObservableFlapMapArray(ObservableSource<T> source,
      Function<? super T, U[]> function) {
    this.source = source;
    this.function = function;
  }

  public final ObservableSource<T> source() {
    return source;
  }

  @Override
  protected void subscribeActual(Observer<? super U> observer) {
    //传入的observer为被订阅的观察者
    // mapObserver也是一个Observer对象，起到了桥接source（被观察者）和Observer（观察者）的作用，
    // mapObserver中的事件最终会分发到传入的observer，在apply方法中，把传入的泛型转成R，这样就完成了map转换的功能
    MergeObserver<T, U> mergeObserver = new MergeObserver<T, U>(observer, function);
    //source订阅mapObserver之后 ，订阅成功后，source的emitter中的事件会分发给mapObserver,
    // mapObserver通过apply方法，把传入的泛型T转成结果R，再通过onNext发送给真正的观察者actual，这样就完成了事件消息的传递和转换
    source.subscribe(mergeObserver);

  }

  static final class MergeObserver<T, U> implements Observer<T> {
    protected final Observer<? super U> actual;
    final Function<? super T, U[]> mapper;

    MergeObserver(Observer<? super U> actual, Function<? super T, U[]> mapper) {
      this.actual = actual;
      this.mapper = mapper;
    }

    @Override
    public void onSubscribe() {
      LogUtil.print("ObservableMap: onSubscribe");
    }

    @Override
    public void onNext(T t) {
      //CheckUtils.checkNotNull(t, "onNext called parameter can not be null");
      U[] p = null;
      try {
        p = mapper.apply(t);
      } catch (Exception e) {
        onError(e);
        e.printStackTrace();
      }
      for (U v : p) {
        actual.onNext(v);
      }
    }

    @Override
    public void onError(Throwable error) {
      actual.onError(error);
    }

    @Override
    public void onComplete() {
      actual.onComplete();
    }
  }


}
