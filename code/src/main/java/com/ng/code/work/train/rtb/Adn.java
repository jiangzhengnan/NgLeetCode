package com.ng.code.work.train.rtb;

import java.util.Random;

public class Adn {
    public String mAdnName;
    public int mPrice;

    public Adn(String mAdnName) {
        this.mAdnName = mAdnName;
    }

    public void getAd(CallBack callBack) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(2000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPrice = random.nextInt(100);
        callBack.onSuccess();
    }

    @Override
    public String toString() {
        return " {" + mAdnName + ':' + mPrice +
                '}';
    }
}
