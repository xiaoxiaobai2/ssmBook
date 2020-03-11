package com.zhanghao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtils implements InvocationHandler {
    private Object obj;
    private Interceptor interceptor = null;

    public static Object getProxy(Object obj, Interceptor interceptor) {
        ProxyUtils proxyUtils = new ProxyUtils();
        proxyUtils.obj = obj;
        proxyUtils.interceptor = interceptor;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), proxyUtils);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;
        boolean exceptionFlag = false;
        interceptor.before();
        try {
            retObj = method.invoke(obj, args);
        } catch (Exception e) {
            exceptionFlag =true;
            e.printStackTrace();
        } finally {
            interceptor.after();
        }
        if (exceptionFlag)
            interceptor.afterThrowing();
        else interceptor.afterReturning();
        return retObj;
    }
}
