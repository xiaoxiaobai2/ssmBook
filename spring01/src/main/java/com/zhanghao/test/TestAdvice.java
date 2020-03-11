package com.zhanghao.test;

import com.zhanghao.service.Juice;
import com.zhanghao.service.impl.JuiceMake2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdvice {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        JuiceMake2 juiceMake2 = (JuiceMake2) applicationContext.getBean("juiceMake2");
        juiceMake2.makeJuice();
    }
}
