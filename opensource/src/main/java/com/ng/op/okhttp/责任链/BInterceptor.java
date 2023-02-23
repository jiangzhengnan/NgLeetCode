package com.ng.op.okhttp.责任链;

import com.ng.op.LogUtil;

public class BInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        LogUtil.print(getClass().getName()+" intercept: " + chain);
        Response response = new Response();
        LogUtil.print("response: " + response);
        response.msg += "B";
        return response;
    }
}
