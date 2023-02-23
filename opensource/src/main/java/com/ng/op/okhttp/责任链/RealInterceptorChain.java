package com.ng.op.okhttp.责任链;

import java.util.List;

public class RealInterceptorChain implements Interceptor.Chain {
    private List<Interceptor> mInterceptors;
    private int mIndex;
    private Request mRequest;

    @Override
    public String toString() {
        return "RealInterceptorChain{" +
                "mRequest=" + mRequest +
                '}';
    }

    public RealInterceptorChain(List<Interceptor> mInterceptors, int mIndex, Request mRequest) {
        this.mInterceptors = mInterceptors;
        this.mIndex = mIndex;
        this.mRequest = mRequest;
    }

    @Override
    public Request request() {
        return mRequest;
    }

    @Override
    public Response proceed(Request request) {
        if (mIndex > mInterceptors.size()) {
            System.out.println("异常，长度超出");
            return null;
        }

        //下一个责任链
        RealInterceptorChain nextChain = new RealInterceptorChain(mInterceptors, mIndex + 1, mRequest);

        //下一个拦截器
        Interceptor nextInterceptor = mInterceptors.get(mIndex);

        Response response = nextInterceptor.intercept(nextChain);
        return response;
    }
}
