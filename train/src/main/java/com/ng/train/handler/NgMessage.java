package com.ng.train.handler;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/02/15
 * @description :
 */
public class NgMessage {
    private static int mNum = 0;

    public void addNum() {
        mNum++;
    }

    public int getNum() {

        return mNum;
    }
}
