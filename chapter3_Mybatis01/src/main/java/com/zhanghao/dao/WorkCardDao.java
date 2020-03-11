package com.zhanghao.dao;

import com.zhanghao.pojo.WorkCard;

public interface WorkCardDao {
    WorkCard getWorkCardByEmpId(int empId);
}
