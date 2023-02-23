package com.ng.op.okhttp.责任链;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Interceptor> mInterceptorList = new ArrayList<>();
        mInterceptorList.add(new AInterceptor());
        mInterceptorList.add(new BInterceptor());

        Request request = new Request();
        request.msg = "start";
        Interceptor.Chain chain = new RealInterceptorChain(mInterceptorList, 0, request);

        System.out.println("result:" + chain.proceed(request));
    }
}
