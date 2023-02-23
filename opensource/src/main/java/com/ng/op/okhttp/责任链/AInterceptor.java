package com.ng.op.okhttp.责任链;

import com.ng.op.LogUtil;

public class AInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        LogUtil.print(getClass().getName()+" intercept: " + chain);
        Request request = chain.request();
        Response response = chain.proceed(request);
        LogUtil.print("response: " + response);
        response.msg += "A";
        return response;
    }
}
