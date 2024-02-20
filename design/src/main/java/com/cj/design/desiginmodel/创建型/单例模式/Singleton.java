package com.cj.design.desiginmodel.创建型.单例模式;

/**
 * 饿汉式
 */
public class Singleton {
    // 1.直接初始化一个实例对象
    private static Singleton instance = new Singleton();

    // 2.静态代码中初始化
    static {//静态代码块中创建单例对象(类加载就会执行静态代码块的代码,一上来就创建)
        instance = new Singleton();
    }

    // private类型的构造函数，确保其它类对象不能new一个本类的实例
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
