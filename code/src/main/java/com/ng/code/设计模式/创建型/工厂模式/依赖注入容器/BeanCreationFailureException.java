package com.ng.code.设计模式.创建型.工厂模式.依赖注入容器;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/08/14
 * @description :
 */
public class BeanCreationFailureException extends Throwable {
    public BeanCreationFailureException(final String s, final ReflectiveOperationException e) {}
}
