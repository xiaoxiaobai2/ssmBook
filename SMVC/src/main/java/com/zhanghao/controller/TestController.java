package com.zhanghao.controller;

import com.zhanghao.dao.EmployeeDao;
import com.zhanghao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller()
@RequestMapping("/controller")
public class TestController {

    @RequestMapping("/test.do")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @Autowired
    private EmployeeDao employeeDao;
    @RequestMapping("/testJson.do")
    public ModelAndView testJson(){
        Employee employee = employeeDao.getEmployee(1);
        System.out.println("employee = " + employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(employee);
        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
}
