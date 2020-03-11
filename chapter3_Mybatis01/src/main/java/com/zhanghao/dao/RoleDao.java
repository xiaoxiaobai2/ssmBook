package com.zhanghao.dao;

import com.zhanghao.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
//    @Select("select * from role")
    List<Role> findAll();
    List<Role> findByName(String name);

    void insert(Role role);
}
