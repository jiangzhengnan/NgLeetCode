package com.ng.op.okhttp;

import com.ng.op.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class Demo {


    public static void main(String[] args) {
        Request request = new Request("www.baidu.com");
        OkHttpClient okHttpClient = new OkHttpClient();
        //添加自定义拦截器
        okHttpClient.addInterceptor(new LogInterceptor());
        Call call = okHttpClient.newCall(request);
        call.execute();
    }

    interface Interceptor {
        Response intercept(Chain chain);

        interface Chain {
            Request request();

            /**
             * 执行
             */
            Response proceed(Request request);
        }
    }

    static class RetryAndFollowUpInterceptor implements Interceptor {
        private static final String TAG = "RetryAndFollowUpInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.print("intercept: " + response);
            return response;
        }
    }

    static class BridgeInterceptor implements Interceptor {
        private static final String TAG = "BridgeInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.print("intercept: " + response);
            return response;
        }
    }

    static class CacheInterceptor implements Interceptor {
        private static final String TAG = "CacheInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.print("intercept: " + response);
            return response;
        }
    }

    static class ConnectInterceptor implements Interceptor {
        private static final String TAG = "ConnectInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.print("intercept: " + response);
            return response;
        }
    }

    static class LogInterceptor implements Interceptor {
        private static final String TAG = "LogInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.print("intercept: " + response);
            return response;
        }
    }

    public static final class CallServerInterceptor implements Interceptor {
        private static final String TAG = "CallServerInterceptor";

        @Override
        public Response intercept(Chain chain) {
            LogUtil.print("intercept: " + chain);
            Response response = new Response(200, "成功");
            return response;
        }
    }

    public interface Call {
        Response execute();
    }

    static class RealCall implements Call {
        final OkHttpClient client;
        final Request originalRequest;

        private RealCall(OkHttpClient client, Request originalRequest) {
            this.client = client;
            this.originalRequest = originalRequest;
        }

        static RealCall newRealCall(OkHttpClient client, Request originalRequest) {
            RealCall call = new RealCall(client, originalRequest);
            return call;
        }

        @Override
        public Response execute() {
            Response result = getResponseWithInterceptorChain();
            return result;
        }

        Response getResponseWithInterceptorChain() {
            List<Interceptor> interceptors = new ArrayList<>();
            //自定义的拦截器
            interceptors.addAll(client.interceptors());

            //自带的五大拦截器
            interceptors.add(new RetryAndFollowUpInterceptor());
            interceptors.add(new BridgeInterceptor());
            interceptors.add(new CacheInterceptor());
            interceptors.add(new ConnectInterceptor());
            //最后一个拦截器
            interceptors.add(new CallServerInterceptor());

            Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, originalRequest, this);
            return chain.proceed(originalRequest);
        }
    }

    static class RealInterceptorChain implements Interceptor.Chain {
        private static final String TAG = "RealInterceptorChain";
        private final List<Interceptor> interceptors;
        private final int index;
        private final Request request;
        private final Call call;

        public RealInterceptorChain(List<Interceptor> interceptors, int index, Request request, Call call) {
            this.interceptors = interceptors;
            this.index = index;
            this.request = request;
            this.call = call;
        }

        @Override
        public Request request() {
            return request;
        }

        @Override
        public Response proceed(Request request) {
            LogUtil.print("proceed: " + request.url);
            RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request, call);
            Interceptor interceptor = interceptors.get(index);
            Response response = interceptor.intercept(next);
            return response;
        }

        @Override
        public String toString() {
            return "RealInterceptorChain{" +
                    "index=" + index +
                    '}';
        }
    }

    static class OkHttpClient {
        private final List<Interceptor> interceptors = new ArrayList<>();

        public void addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
        }

        public List<Interceptor> interceptors() {
            return interceptors;
        }

        RealCall newCall(Request request) {
            return RealCall.newRealCall(this, request);
        }
    }

    static class Request {
        final String url;

        public Request(String url) {
            this.url = url;
        }
    }

    static class Response {
        final int code;
        final String message;

        public Response(int code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
