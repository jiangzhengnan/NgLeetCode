package com.cj.design.desiginmodel.结构型.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : 
 * @creation : 2022/10/01
 * @description :
 *
 * 主要是为了解决静态代理的两个缺点
 */
public class 动态代理 {
    //定义一个绘制接口，原始类
    interface DrawApi {
        void draw();

        void refresh();
    }

    //定义具体的圆形绘制方案，被代理类
    static class CircleDraw implements DrawApi {

        @Override
        public void draw() {
            System.out.println("画一个圆");
        }

        @Override
        public void refresh() {
            System.out.println("刷新");
        }
    }

    //定义一个测算接口，原始类
    interface CalculateApi {
        void calculate();
    }

    //定义圆形的测算方案，被代理类
    static class CircleCalculate implements CalculateApi {

        @Override
        public void calculate() {
            System.out.println("测量圆的尺寸");
        }
    }

    //动态代理实现类
    //优点：不需要将将原始类中的所有的方法，都重新实现一遍
    public static class DrawProxy {
        public DrawProxy() {
        }

        public Object createProxy(Object proxiedObject) {
            Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
            DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
            return Proxy.newProxyInstance(proxiedObject.getClass()
                                                       .getClassLoader(), interfaces, handler);
        }

        //代理类的实现处理接口
        private class DynamicProxyHandler implements InvocationHandler {
            private final Object drawImpl;

            public DynamicProxyHandler(Object drawImpl) {
                this.drawImpl = drawImpl;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                before();
                Object result = method.invoke(drawImpl, args);
                String apiName = drawImpl.getClass().getName() + ":" + method.getName();
                System.out.println("执行方法:" + apiName);
                after();
                return result;
            }

            private void after() {
                System.out.println("执行后 做一些事情");
            }

            private void before() {
                System.out.println("执行前 做一些事情");
            }
        }
    }

    public static void main(String[] args) {
        DrawProxy proxy = new DrawProxy();
        DrawApi drawApi = (DrawApi) proxy.createProxy(new CircleDraw());
        drawApi.draw();

        //完美解决 原始类有多个的问题
        CalculateApi calculateApi = (CalculateApi) proxy.createProxy(new CircleCalculate());
        calculateApi.calculate();
    }

}
