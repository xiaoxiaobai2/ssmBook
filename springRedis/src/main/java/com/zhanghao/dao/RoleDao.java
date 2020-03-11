package com.zhanghao.dao;

import com.zhanghao.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 逻辑层接口
 */
@Repository("roleDao")
public interface RoleDao {


    int insertRole(Role role);
    int update(Role role);
    int delete(int id);
    Role getRole(int id);
    List<Role> findRoles(@Param("roleName") String roleName);

}
