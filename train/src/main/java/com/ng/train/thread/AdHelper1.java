package com.ng.train.thread;

import com.ng.base.LogUtil;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/24
 * @description :
 */
public class AdHelper1 extends BaseAd{

    String data = "";

    public void readData(){
        readLock();
        LogUtil.print("1 read :" + data);
        readUnLock();
    }

    public void writeData() {
        writeLock();
        data += "+write";
        LogUtil.print("1 write :" + data);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writeUnlock();
    }

}
