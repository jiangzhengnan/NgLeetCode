package com.cj.design.desiginmodel.结构型.代理模式;

/**
 * 概念：
 * 在不直接修改原有类的前提下对原有类的方法功能进行扩展。
 * 通过引入代理类(构参带入)来给原始类附加功能
 *
 * 参与角色：
 *   Subject：抽象主题，定义了被代理对象和代理对象的共同接口
 *   RealSubject：具体主题，即被代理对象
 *   Proxy：代理对象，包装了被代理对象，提供与被代理对象相同的接口，但可以在这些接口前后做一些额外的事情。
 */

public class 静态代理 {

    // Subject
    interface Calculator {
        int add(int a, int b);
    }

    // RealSubject
    static class SimpleCalculator implements Calculator {
        @Override
        public int add(int a, int b) {
            int result = a + b;
            System.out.printf("%d + %d = %d\n", a, b, result);
            return result;
        }
    }

    // Proxy
    static class LoggingCalculatorProxy implements Calculator {
        private Calculator calculator;

        public LoggingCalculatorProxy(Calculator calculator) {
            this.calculator = calculator;
        }

        @Override
        public int add(int a, int b) {
            System.out.printf("start calculating %d + %d\n", a, b);
            int result = calculator.add(a, b);
            System.out.println("calculation finished");
            return result;
        }
    }

    // 测试
    public static void main(String[] args) {
        Calculator calculator = new SimpleCalculator();
        Calculator loggingCalculator = new LoggingCalculatorProxy(calculator);

        loggingCalculator.add(1, 2);
    }

}
