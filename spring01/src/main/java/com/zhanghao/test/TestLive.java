package com.zhanghao.test;

import com.zhanghao.service.Juice;
import com.zhanghao.service.impl.JuiceMake;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLive {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Juice juice = (Juice) applicationContext.getBean("juice");
        juice.makeJuice();
        applicationContext.close();
    }
}
