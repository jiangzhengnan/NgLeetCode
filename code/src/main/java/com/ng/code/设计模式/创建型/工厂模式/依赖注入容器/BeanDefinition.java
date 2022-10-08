package com.ng.code.设计模式.创建型.工厂模式.依赖注入容器;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/08/14
 * @description :
 */
public class BeanDefinition {

    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false;
    // 省略必要的getter/setter/constructors


    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(final List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(final Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(final boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }


    public static enum Scope {
        SINGLETON,
        PROTOTYPE
    }

    public static class ConstructorArg {
        private boolean isRef;
        private Class type;
        private Object arg;
        // 省略必要的getter/setter/constructors


        public boolean isRef() {
            return isRef;
        }

        public void setRef(final boolean ref) {
            isRef = ref;
        }

        public Class getType() {
            return type;
        }

        public void setType(final Class type) {
            this.type = type;
        }

        public Object getArg() {
            return arg;
        }

        public void setArg(final Object arg) {
            this.arg = arg;
        }
    }
}
