package com.cj.design.desiginmodel.行为型.观察者模式.rxjava.map;


import androidx.annotation.NonNull;

//T表示输入值，R表示输出值，把 T转换成 R。
public interface Function<T, R> {
  /**
   * Apply some calculation to the input value and return some other value.
   * @param t the input value
   * @return the output value
   * @throws Exception on error
   */
  R apply(@NonNull T t) throws Exception;
}
