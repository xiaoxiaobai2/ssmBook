package com.zhanghao.proxy;

public interface Interceptor {
    void before();
    void after();
    void afterThrowing();
    void afterReturning();
}
