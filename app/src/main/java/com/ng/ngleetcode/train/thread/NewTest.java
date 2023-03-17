package com.ng.ngleetcode.train.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author : 
 * @creation : 2023/03/03
 * @description :
 */
public class NewTest {
    private final Map<String, String> nodeMap = new HashMap<>();

    private static volatile long startTime = 0;

    private void put() {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {

                        Random random = new Random();
                        String key = random.nextInt(1000) + "";
                        synchronized (nodeMap) {
                            nodeMap.put(key, key);
                        }
                    }
                }
            }).start();
        }

    }

    private void print() {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        String res = "";
                        synchronized (nodeMap) {

                            for (String key : nodeMap.keySet()) {
                                res += key + " ";
                            }
                        }
                        System.out.println(Thread.currentThread()
                                                 .getName() + " " + (System.currentTimeMillis() - startTime) + "    " + res + " ");
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        NewTest newTest = new NewTest();
        newTest.put();
        newTest.print();

    }

}
