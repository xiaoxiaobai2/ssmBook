package com.zhanghao.proxy;

import com.zhanghao.service.Juice;
import com.zhanghao.service.impl.JuiceMake;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
    private Object juiceMake2;

    public static void main(String[] args) {
        Interceptor juiceInterceptor = new JuiceInterceptor();
        Juice juice = new JuiceMake("西瓜汁","大杯","加冰");
        Juice proxy = (Juice) ProxyUtils.getProxy(juice, juiceInterceptor);

        proxy.makeJuice();
    }
}
