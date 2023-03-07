package com.ng.code.menu.多线程;

import com.ng.code.util.Solution;

/**
 * @author :
 * @creation : 2022/04/19
 * @description :
 * 需要参考
 * https://blog.csdn.net/skz980619/article/details/119703042
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_生产者消费者模式 {
    public final static int MAX_PRODUCT = 100;
    public final static int MIN_PRODUCT = 0;

    private volatile int product;

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void produce() {
        if (this.product >= MAX_PRODUCT) {
            try {
                wait();
                System.out.println("产品已满,请稍候再生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        this.product++;
        System.out.println("生产者生产第" + this.product + "个产品.");
        notifyAll();   //通知等待区的消费者可以取出产品了
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void consume() {
        if (this.product <= MIN_PRODUCT) {
            try {
                wait();
                System.out.println("缺货,稍候再取");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("消费者取走了第" + this.product + "个产品.");
        this.product--;
        notifyAll();   //通知等待去的生产者可以生产产品了
    }

    public static void main(String[] args) {
        Ⅰ_生产者消费者模式 test = new Ⅰ_生产者消费者模式();
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    test.produce();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    test.consume();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();
    }
}
