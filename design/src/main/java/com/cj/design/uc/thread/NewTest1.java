package com.cj.design.uc.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author :
 * @creation : 2023/03/03
 * @description :
 */
public class NewTest1 {
    private final Map<String, String> nodeMap = new HashMap<>();

    private static volatile long startTime = 0;

    private void put() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {

                        Random random = new Random();
                        String key = random.nextInt(1000) + "";
                        nodeMap.put(key, key);
                    }
                }
            }).start();
        }

    }

    private void print() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 10; j++) {
                            String res = "";

                            for (String key : nodeMap.keySet()) {
                                res += key + " ";
                            }
                            System.out.println(Thread.currentThread()
                                                     .getName() + " " + (System.currentTimeMillis() - startTime) );
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        NewTest1 newTest = new NewTest1();
        newTest.put();
        newTest.print();

    }

}
