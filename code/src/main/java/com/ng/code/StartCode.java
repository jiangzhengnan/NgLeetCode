package com.ng.code;

import com.ng.code.util.ProblemUtil;
import com.ng.code.util.ProgressUtil;

/**
 * 随机抽题模拟器
 */
public final class StartCode {

    public static void main(String[] args) {
        //当前进度
        //refreshLocalProjects();

        //随机得到几个题目
        //ProblemUtil.getRandomTest(6);
    }

    //刷新本地目录，补充题库
    private static void refreshLocalProjects() {
        ProgressUtil.getNowProgress();
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
