package com.ng.train.thread;

import com.ng.base.LogUtil;

/**
 * @author : 
 * @creation : 2023/02/24
 * @description :
 */
public class AdHelper2 extends BaseAd{

    String data = "";

    public void readData(){
        readLock();
        LogUtil.print("2 read :" + data);
        readUnLock();
    }

    public void writeData() {
        writeLock();
        data += " write";
        LogUtil.print("2 write :" + data);
        writeUnlock();
    }

}
