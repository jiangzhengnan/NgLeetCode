package com.ng.train.thread;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

/**
 * @author : 
 * @creation : 2023/02/15
 * @description :
 * ReentrantLock lock = new ReentrantLock();
 * ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
 */
public class Test {


    public static void main(String[] args) {
        Handler handler = new Handler() {
            @Override
            public void dispatchMessage(@NonNull final Message msg) {
                super.dispatchMessage(msg);
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);

        AdHelper1 adHelper1 = new AdHelper1();
        AdHelper2 adHelper2 = new AdHelper2();

        new Thread(new Runnable() {
            @Override
            public void run() {

                adHelper1.writeData();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                adHelper2.writeData();

            }
        }).start();
    }

}
