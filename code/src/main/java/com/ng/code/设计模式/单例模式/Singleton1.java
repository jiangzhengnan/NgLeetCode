package com.ng.code.设计模式.单例模式;

/**
 * 懒汉式(双重检查锁 = 线程安全 + lazy):
 */
public class Singleton1 {
    // 1.构造器私有化,外部类不能new创建实例
    private Singleton1() {
    }

    // 2.定义volatile的实例
    private static volatile Singleton1 instance;

    // 3.只暴露一个public方法getInstance()创建单例,使用的时候才创建,所以叫懒汉式
    // 分析:volatile防止指令重排序,防止返回没有初始化的实例
    public static Singleton1 getInstance() {
        if (instance == null) { // 空则加锁创建,非空则直接返回
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
