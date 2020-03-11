package com.zhanghao.dao;

import com.zhanghao.pojo.EmployeeTask;

import java.util.ArrayList;

public interface EmployeeTaskDao {
    ArrayList<EmployeeTask> getEmployeeTaskByEmpId(int empId);
}
