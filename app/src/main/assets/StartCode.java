package com.ng.ngleetcode.start;

import com.ng.ngleetcode.util.ProgressUtil;

/**
 * 随机抽题模拟器
 */
public class StartCode {

    public static void main(String[] args) {
        //当前进度
        //ProgressUtil.getNowProgress();

        //随机得到几个题目
        //ProblemUtil.getRandomTest(5);

        //输出目录到readme
        ProgressUtil.refreshReadMe();

        //LogUtil.pring(ProgressUtil.getRandomProblemLink());
    }

}
