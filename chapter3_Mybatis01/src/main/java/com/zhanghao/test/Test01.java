package com.zhanghao.test;

import com.zhanghao.dao.*;
import com.zhanghao.pojo.*;
import com.zhanghao.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;


public class Test01 {
    public static void main(String[] args) throws IOException {
//        testTask();
//        testWorkCard();
//        testEmployeeTask();
//        testMaleHealthForm();
//        testFemaleHealthForm();
        testEmployee();
    }

    private static void testTask(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        TaskDao mapper = sqlSession.getMapper(TaskDao.class);
        Task task = mapper.getTask(1L);
        System.out.println("task = " + task);
    }

    private static void testWorkCard(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        WorkCardDao mapper = sqlSession.getMapper(WorkCardDao.class);
        WorkCard workCard = mapper.getWorkCardByEmpId(1);
        System.out.println("workCard = " + workCard);
    }

    private static void testEmployeeTask(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        EmployeeTaskDao mapper = sqlSession.getMapper(EmployeeTaskDao.class);
        ArrayList<EmployeeTask> employeeTasks = mapper.getEmployeeTaskByEmpId(1);
        System.out.println("employeeTasks = " + employeeTasks);
    }

    private static void testMaleHealthForm(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        MaleHealthFormDao mapper = sqlSession.getMapper(MaleHealthFormDao.class);
        MaleHealthForm task = mapper.getMaleHealthFormByEmpId(1);
        System.out.println("task = " + task);
    }

    private static void testFemaleHealthForm(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        FemaleHealthFormDao mapper = sqlSession.getMapper(FemaleHealthFormDao.class);
        FemaleHealthForm task = mapper.getFemaleHealthFormByEmpId(2);
        System.out.println("task = " + task);
    }

    private static void testEmployee(){
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        Employee employee = mapper.getEmployee(1);

//        System.out.println("task = " + task);
        //测试一级缓存
//        Employee task2 = mapper.getEmployee(1);
//        System.out.println("task = " + task2);
    }
}
