package com.zhanghao.proxy;

import org.aspectj.lang.annotation.*;

@Aspect
public class JuiceInterceptor implements Interceptor {

    @Pointcut("execution(* com.zhanghao.service.impl.*.*(..))")
    public void pc(){

    }

    @Before("pc()")
    public void before() {
        System.out.println("JuiceInterceptor.before");
    }

    @After("pc()")
    public void after() {
        System.out.println("JuiceInterceptor.after");
    }

    @AfterThrowing("pc()")
    public void afterThrowing() {
        System.out.println("JuiceInterceptor.afterThrowing");
    }

    @AfterReturning("pc()")
    public void afterReturning() {
        System.out.println("JuiceInterceptor.afterReturning");
    }
}
