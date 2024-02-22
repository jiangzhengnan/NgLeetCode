package com.cj.design.desiginmodel.结构型.桥接模式;

/**
 * 概念：
 * 与代理模式容易混淆。一个类存在两个（或多个）独立变化的维度，我们通过组合的方式，让这两个（或多个）维度可以独立进行扩展。
 * 通俗的说，就是一个类如果有多个属性需要变化，在进行扩展(继承)时会导致新增的子类指数增加，所以需要通过桥接模式将继承改为组合的方式，
 * 将属性的维度解藕出来，将抽象与原本复杂的实现分离，提高扩展能力。
 * 区别：
 * 与代理模式相比，假设将一个纬度抽象以后，执行的方法命名为a()
 * 代理模式侧重于 通过代理的方式扩展被代理类的功能
 * 桥接模式侧重于 通过组合的方式让不同的纬度的原始类可以搭配使用
 */
public class 桥接模式 {

    // Implementor
    interface Engine {
        void start();

        void stop();
    }

    // ConcreteImplementor
    static class GasolineEngine implements Engine {
        @Override
        public void start() {
            System.out.println("启动汽油发动机");
        }

        @Override
        public void stop() {
            System.out.println("熄火汽油发动机");
        }
    }

    static class ElectricEngine implements Engine {
        @Override
        public void start() {
            System.out.println("启动电动发动机");
        }

        @Override
        public void stop() {
            System.out.println("停止电动发动机");
        }
    }

    // Abstraction
    static abstract class Car {
        private Engine engine;

        public Car(Engine engine) {
            this.engine = engine;
        }

        public void start() {
            engine.start();
        }

        public void stop() {
            engine.stop();
        }

        public abstract void drive();
    }

    // RefinedAbstraction
    static class Benz extends Car {
        public Benz(Engine engine) {
            super(engine);
        }

        @Override
        public void drive() {
            System.out.println("Benz is driving...");
            super.start();
            super.stop();
        }
    }

    static class BMW extends Car {
        public BMW(Engine engine) {
            super(engine);
        }

        @Override
        public void drive() {
            System.out.println("BMW is driving...");
            super.start();
            super.stop();
        }
    }

    /**
     * 这个示例中，我们展示了侨接模式如何工作，以解决代码中的相互耦合关系问题。
     * 在这个示例中，Engine 是一个接口，GasolineEngine 和 ElectricEngine 是其一个实现。
     * Car 是另一个抽象类，拥有一个成员变量 Engine。
     * 类似于 Benz 和 BMW，是一个扩展了 Car 的类，因此可以继承其父类的成员变量和方法，并在其内部使用 Engine。
     * 这就形成了中间层，使 Engine 和 Car 之间的耦合变得松散。
     */
    public static void main(String[] args) {
        Engine gasolineEngine = new GasolineEngine();
        Benz benz = new Benz(gasolineEngine);
        benz.drive();

        Engine electricEngine = new ElectricEngine();
        BMW bmw = new BMW(electricEngine);
        bmw.drive();
    }
}
