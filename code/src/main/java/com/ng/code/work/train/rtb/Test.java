package com.ng.code.work.train.rtb;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<Adn> mTaskAdnList = new ArrayList<>();
        //创建广告
        for (int i = 0; i < 50; i++) {
            mTaskAdnList.add(new Adn("厂商:" + i));
        }

        RtbEngine rtbEngine = new RtbEngine();
        rtbEngine.execute(mTaskAdnList);

    }

}
