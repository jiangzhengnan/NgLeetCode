package com.ng.code;

import com.ng.code.util.ProblemUtil;
import com.ng.code.util.ProgressUtil;

/**
 * 随机抽题模拟器
 */
public class StartCode {

    public static void main(String[] args) {
        //当前进度
        ProgressUtil.getNowProgress();

        //随机得到几个题目
        //ProblemUtil.getRandomTest(5);

    }

    private static void refreshLocalProjects() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //输出目录到readme
                ProgressUtil.refreshReadMe();
                //输出所有题目到assets
                ProblemUtil.copyJavaToAssets();
            }
        }).start();
    }

}
