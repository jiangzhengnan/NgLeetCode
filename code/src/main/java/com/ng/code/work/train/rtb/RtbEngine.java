package com.ng.code.work.train.rtb;

import com.ng.base.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RtbEngine {

    private int MAX_SIZE = 20;

    private AtomicInteger mSucNum = new AtomicInteger();

    private List<Adn> mSucAdn = new ArrayList<>();

    public void execute(List<Adn> mAdns) {

        for (Adn adn : mAdns) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    adn.getAd(new CallBack() {
                        @Override
                        public void onSuccess() {
                            if (mSucNum.getAndAdd(1) < MAX_SIZE) {
                                mSucAdn.add(adn);
                            }

                            LogUtil.print(mSucAdn.size());
                        }
                    });
                }
            };
            new Thread(runnable).start();
        }
    }

}
