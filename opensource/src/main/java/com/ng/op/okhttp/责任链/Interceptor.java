package com.ng.op.okhttp.责任链;

//拦截器
public interface Interceptor {

    //构建责任链
    interface Chain {
        Request request();

        Response proceed(Request request );
    }

    //负责处理实际事务
    Response intercept(Chain request);

}
