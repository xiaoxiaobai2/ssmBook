package com.zhanghao.test;

import com.zhanghao.dao.EmployeeDao;
import com.zhanghao.pojo.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatisSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        EmployeeDao employeeDao = applicationContext.getBean(EmployeeDao.class);
        Employee employee = employeeDao.getEmployee(1);
        System.out.println("employee = " + employee);
    }
}
