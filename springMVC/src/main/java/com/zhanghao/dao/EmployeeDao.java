package com.zhanghao.dao;

import com.zhanghao.pojo.Employee;
import org.springframework.stereotype.Repository;

//@Repository("employeeDao")
public interface EmployeeDao {
    Employee getEmployee(int id);
}
