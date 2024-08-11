package com.cj.design.desiginmodel.行为型.观察者模式.rxjava;


import java.util.ArrayList;
import java.util.List;

import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.map.Function;
import com.cj.design.desiginmodel.行为型.观察者模式.rxjava.thread.Schedulers;

public class AAAAAAA_TestMain {

  public static void main(String[] args) {
    //被观察者1
    Observable<String> observableA = Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            println("A 发送第一个onNext");
            emitter.onNext("1");
//            println("A 发送第二个onNext");
//            emitter.onNext("2");
//            println("A 发送onComplete");
//            emitter.onComplete();
//            emitter.onError(new Throwable("A test error"));
          }
        })
        .observeOn(Schedulers.newThread())
//        .observeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
        .subscribeOn(Schedulers.newThread())
        ;

    //被观察者2
    Observable<String> observableB = Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            println("B 发送第一个onNext");
            emitter.onNext("1");
            println("B 发送第二个onNext");
            emitter.onNext("2");
            println("B 发送onComplete");
            emitter.onComplete();
            emitter.onError(new Throwable("B test error"));
          }
        })
//        .flatArray(new Function<String, String[]>() {
//          @Override
//          public String[] apply(String s) throws Exception {
//            String[] res = new String[2];
//            res[0] = "first :" + s;
//            res[1] = "second :" + s;
//            return res;
//          }
//        })
        .map(new Function<String, String>() {
          @Override
          public String apply(String integer) throws Exception {
            return "A" + integer;
          }
        })
        .flatMap(new Function<String, Iterable<String>>() {
          @Override
          public Iterable<String> apply(String s) throws Exception {
            List<String> list = new ArrayList<>();
            list.add("first : " + s);
            list.add("second :" + s);
            return list;
          }
        })
    ;

    //观察者
    Observer<String> observer = new Observer<String>() {
      @Override
      public void onSubscribe() {
        println("观察者 被订阅");
      }

      @Override
      public void onNext(String value) {
        println("观察者 收到 onNext value=" + value);
      }

      @Override
      public void onError(Throwable e) {
        println("观察者 收到 onError e=" + e);
      }

      @Override
      public void onComplete() {
        println("观察者 收到 onComplete");
      }
    };
    observableA.subscribe(observer);


//    Observable.zip(observableA, observableB, new BiFunction<String, String, String>() {
//
//      @Override
//      public String apply(String s, String s2) throws Exception {
//        return "zip : "+ s +" "+s2;
//      }
//    }).subscribe(observer);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void println(String str) {

    System.out.println("线程：" + Thread.currentThread().getName() + "| " + str);
  }
}
