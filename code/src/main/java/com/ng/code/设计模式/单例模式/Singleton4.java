package com.ng.code.设计模式.单例模式;

/**
 * 枚举
 * 可能还存在反射攻击或者反序列化攻击
 */
public class Singleton4 {
    private Singleton4() {
    }

    public static enum SingletonEnum {
        INSTANCE;
        private Singleton4 instance = null;

        private SingletonEnum() {
            instance = new Singleton4();
        }

        public Singleton4 getInstance() {
            return instance;
        }
    }

    //对外暴露一个获取User对象的静态方法
    public static Singleton4 getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }
}