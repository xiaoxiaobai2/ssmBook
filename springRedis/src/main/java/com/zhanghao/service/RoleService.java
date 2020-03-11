package com.zhanghao.service;

import com.zhanghao.pojo.Role;

import java.util.List;

/**
 * 角色业务接口
 */
public interface RoleService {
    Role insertRole(Role role);
    Role update(Role role);
    int delete(int id);
    Role getRole(int id);
    List<Role> findRoles(String roleName);
}
