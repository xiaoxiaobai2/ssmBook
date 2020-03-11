package com.zhanghao.service.impl;

import com.zhanghao.service.Juice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JuiceMake implements Juice, BeanNameAware, BeanFactoryAware, BeanPostProcessor, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;
    private String cup;
    private String suger;

    public JuiceMake(String name, String cup, String suger) {
        this.name = name;
        this.cup = cup;
        this.suger = suger;
    }

    public void makeJuice() {
        System.out.println("要" + cup +name+","+suger);
    }

    public void init(){
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "执行自定义初始化方法" );
    }


    public void myDestroy(){
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "执行自定义销毁方法" );
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "调用BeanFactoryAware的SetBeanFactory的方法" );
    }

    public void setBeanName(String name) {
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "调用setBeanNameAware方法的setBeanName方法" );
    }

    public void destroy() throws Exception {
        System.out.println("调用disposable的destroy的方法" );
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "afterProper" );
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【"+this.getClass().getSimpleName()+"】" + "setApplicationContext" );
    }

}
