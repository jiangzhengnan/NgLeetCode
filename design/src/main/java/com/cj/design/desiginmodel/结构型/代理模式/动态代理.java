package com.cj.design.desiginmodel.结构型.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 主要是为了解决静态代理的两个缺点
 */
public class 动态代理 {

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

    // InvocationHandler
    static class LoggingInvocationHandler implements InvocationHandler {
        private Object target;

        public LoggingInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.printf("start " + method.getName() + " % d + % d\n ", args[0], args[1]);
            Object result = method.invoke(target, args);
            System.out.println("calculation finished");
            return result;
        }
    }

    /**
     * 在这个示例中，我们使用了 Proxy.newProxyInstance() 方法来创建动态代理对象，该方法接受三个参数：
     * 类加载器：传入被代理对象的类加载器
     * 接口数组：需要代理的接口数组
     * 调用处理器：实现 InvocationHandler 接口的类，代理对象的方法调用会被转发到该调用处理器中。
     * -
     * 动态代理的优点是可以在运行时创建代理对象，不需要编写具体的实现代码，
     * 使得代理对象的创建更加灵活，适用于一些需要写大量重复代码的场景。
     */
    public static void main(String[] args) {
        Calculator calculator = new SimpleCalculator();

        Calculator loggingCalculator = (Calculator) Proxy.newProxyInstance(
            Calculator.class.getClassLoader(),
            new Class<?>[]{Calculator.class},
            new LoggingInvocationHandler(calculator)
        );

        loggingCalculator.add(1, 2);
    }

}
