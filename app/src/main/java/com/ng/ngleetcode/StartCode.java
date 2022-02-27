package com.ng.ngleetcode;

import com.ng.ngleetcode.util.LogUtil;
import com.ng.ngleetcode.util.ProblemUtil;
import com.ng.ngleetcode.util.ProgressUtil;

/**
 * 随机抽题模拟器
 */
public class StartCode {

    public static void main(String[] args) {
        ProgressUtil.getNowProgress();
        LogUtil.pring(" ");
        ProblemUtil.getRandomTest(5);
    }

}
