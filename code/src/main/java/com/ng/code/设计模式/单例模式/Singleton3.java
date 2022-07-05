package com.ng.code.设计模式.单例模式;

/**
 * 静态内部类:
 */
public class Singleton3 {
    // 1.构造器私有化,外部类不能new创建实例
    private Singleton3() {
    }
    // 2.静态内部类创建instance(没调用时不会创建instance)
    static class Inner {
        private static final Singleton3 instance = new Singleton3();
    }
    // 3.只暴露一个public方法getInstance()创建单例,使用的时候才创建,所以叫懒汉式
    public static Singleton3 getInstance() {
        return Inner.instance;
    }
}
