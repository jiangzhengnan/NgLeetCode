package com.ng.code.work.train.thread;

/*

 // **************************Synchronized的使用方式**************************
 // 1.用于代码块
 synchronized (this) {}
 // 2.用于对象
 synchronized (object) {}
 // 3.用于方法
 public synchronized void test () {}
 // 4.可重入
 for (int i = 0; i < 100; i++) {
     synchronized (this) {}
 }

 // **************************ReentrantLock的使用方式**************************
 public void test () throw Exception {
     // 1.初始化选择公平锁、非公平锁
     ReentrantLock lock = new ReentrantLock(true);
     // 2.可用于代码块
     lock.lock();
     try {
         try {
             // 3.支持多种加锁方式，比较灵活; 具有可重入特性
             if(lock.tryLock(100, TimeUnit.MILLISECONDS)){ }
         } finally {
             // 4.手动释放锁
             lock.unlock()
         }
     } finally {
         lock.unlock();
     }
 }

 */
public class Demo {

    public static void main(String[] args) {

    }
}
