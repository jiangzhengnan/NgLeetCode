package com.ng.code.perday;

// 写一个单例模式
public class TestSingleClass {

  private TestSingleClass() {}

  private static volatile TestSingleClass mInstance;

  public static TestSingleClass getInstance() {
    if (mInstance == null) {
      synchronized (TestSingleClass.class) {
        if (mInstance == null) {
          mInstance = new TestSingleClass();
        }
      }
    }
    return mInstance;
  }

}
