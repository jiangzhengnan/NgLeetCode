package com.ng.ngleetcode.train.thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : 
 * @creation : 2023/02/24
 * @description :
 */
public class BaseAd {

    protected final ReentrantLock sInitLock = new ReentrantLock();
    protected final ReentrantReadWriteLock sInitReadWriteLock = new ReentrantReadWriteLock();
    protected boolean mUserReadWriteLock;

    protected void readLock() {
        if (mUserReadWriteLock) {
            sInitReadWriteLock.readLock().lock();
        } else {
            sInitLock.lock();
        }
    }

    protected void readUnLock() {
        if (mUserReadWriteLock) {
            sInitReadWriteLock.readLock().unlock();
        } else {
            sInitLock.unlock();
        }
    }

    protected void writeLock() {
        if (mUserReadWriteLock) {
            sInitReadWriteLock.writeLock().lock();
        } else {
            sInitLock.lock();
        }
    }

    protected void writeUnlock() {
        if (mUserReadWriteLock) {
            sInitReadWriteLock.writeLock().unlock();
        } else {
            sInitLock.unlock();
        }
    }
}
