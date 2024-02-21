package com.cj.design.desiginmodel.行为型.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 概念：
 * 在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知
 * 区别:
 * 观察者模式是一对多，生产者消费者是多对多
 * 示例：
 * 目标类负责实现对观察者的增删与通知，观察者负责接受通知。
 */
public class ObserverModel {

    static class Message {
        public int code;
        public Object msg;
    }

    /**
     * 主题接口
     * 每个主题可以有许多观察者
     * 提供增加，删除，通知观察者对象的抽象方法
     */
    interface Subject {
        void registerObserver(Observer observer);

        void removeObserver(Observer observer);

        void notifyObservers(Message message);
    }

    /**
     * 具体主题实现类
     * 抽象目标类中的方法，通过集合保存观察者
     */
    static class ConcreteSubject implements Subject {
        private List<Observer> observers = new ArrayList<Observer>();

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers(Message message) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }

    /**
     * 抽象观察者，包含一个更新自己的抽象方法
     */
    interface Observer {
        void update(Message message);
    }

    /**
     * 具体观察者，实现抽象观察者中的抽象更新方法
     */
    static class ConcreteObserverOne implements Observer {
        @Override
        public void update(Message message) {
            //TODO: 获取消息通知，执行自己的逻辑...
            System.out.println("ConcreteObserverOne is notified.");
        }
    }

    static class ConcreteObserverTwo implements Observer {
        @Override
        public void update(Message message) {
            //TODO: 获取消息通知，执行自己的逻辑...
            System.out.println("ConcreteObserverTwo is notified.");
        }
    }

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new Message());
    }
}
