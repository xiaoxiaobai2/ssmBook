package com.zhanghao.dao;

import com.zhanghao.pojo.Employee;

//@Repository("employeeDao")
public interface EmployeeDao {
    Employee getEmployee(int id);
}
