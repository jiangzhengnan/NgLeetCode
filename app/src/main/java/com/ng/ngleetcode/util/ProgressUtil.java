package com.ng.ngleetcode.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;


/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/02/27
 * @description :
 * 进度统计
 */
public class ProgressUtil {

    public static void getNowProgress() {
        Set<Class<?>> codeSet = ProblemUtil.getClassSet("com.ng.ngleetcode.code");
        ArrayList<Class<?>> list = new ArrayList(codeSet);

        int easy = 0, hard = 0;
        for (Class clazz : list) {
            // 获取类上的注解
            Annotation[] annos = clazz.getAnnotations();
            for (Annotation anno : annos) {
                Solution solution = (Solution) anno;
                if (solution.easy() > 0) {
                    easy++;
                }
                if (solution.hard() > 0) {
                    hard++;
                }
            }
        }
        LogUtil.pring("题库总数: " + list.size() + " 简单完成: " + easy + " 困难完成: " + easy);
    }
}
