package com.ng.code.menu.模拟;

import com.ng.code.util.Solution;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/04/19
 * @description :
 * 需要参考
 * https://blog.csdn.net/skz980619/article/details/119703042
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 生产者消费者模式 {

    public static void main(String[] args) {
        BlockingDeque<Data> queue = new LinkedBlockingDeque<>(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer1.stop();
        producer2.stop();
        producer3.stop();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    //生产者
    public static class Producer implements Runnable {
        //共享阻塞队列
        private BlockingDeque<Data> queue;
        //是否还在运行
        private volatile boolean isRunning = true;
        //id生成器
        private static AtomicInteger count = new AtomicInteger();
        //生成随机数
        private static Random random = new Random();

        public Producer(BlockingDeque<Data> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (isRunning) {
                    //模拟注水耗时
                    Thread.sleep(random.nextInt(1000));
                    int num = count.incrementAndGet();
                    Data data = new Data(num, num);
                    System.out.println("当前>>注水管:" + Thread.currentThread().getName() + "注水容量(L):" + num);
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                        System.out.println("注水失败...");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            isRunning = false;
        }
    }

    //消费者
    public static class Consumer implements Runnable {

        private BlockingDeque<Data> queue;

        private static Random random = new Random();

        public Consumer(BlockingDeque<Data> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Data data = queue.take();
                    //模拟抽水耗时
                    Thread.sleep(random.nextInt(1000));
                    if (data != null) {
                        System.out.println("当前<<抽水管:" + Thread.currentThread().getName() + ",抽取水容量(L):" + data.getNum());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //数据类
    public static class Data {
        private int id;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Data(int id, int num) {
            this.id = id;
            this.num = num;
        }

        public Data() {

        }
    }
}
