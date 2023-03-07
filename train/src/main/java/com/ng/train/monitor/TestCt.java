package com.ng.train.monitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.core.R;
import com.ng.base.LogUtil;
import com.ng.train.monitor.utils.NodeUtil;
import com.ng.train.monitor.utils.TimeLog;
import org.json.JSONObject;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2023/03/03
 * @description :
 */
public class TestCt {
    private CtMonitor mCtMonitor;

    public TestCt() {
        mCtMonitor = new CtMonitor();
    }

    public static void main(String[] args) {
        recordGlobalCt();
        printGlobalCt();

    }


    private static void recordGlobalCt() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        for (int j = 0; j < 100; j++) {

                            Random random = new Random();
                            String key = random.nextInt(1000) + "";
                            Map<String, CtNode> ctMap1 = GlobalCtManager.INSTANCE.getMonitor()
                                                                                 .getNodeMap();

                            ctMap1.put(key, ctMap1.get(key));
                        }
                    }

                }
            }).start();
        }

    }

    private static void printGlobalCt() {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        Map<String, CtNode> ctMap1 = GlobalCtManager.INSTANCE.getMonitor()
                                                                             .getNodeMap();
                        for (String key : ctMap1.keySet()) {
                            CtNode node1 = ctMap1.get(key);
                            System.out.println(Thread.currentThread()
                                                     .getName() + " " + node1.toString());
                        }

                    }
                }
            }).start();
        }
    }

}
