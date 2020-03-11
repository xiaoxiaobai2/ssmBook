package com.zhanghao.service.impl;

import com.zhanghao.dao.RoleDao;
import com.zhanghao.pojo.Role;
import com.zhanghao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /*
        cachePut 直接从更新数据库，将返回结果在缓存中更新
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager",key = "'redis_role_' + #result.id")
    public Role insertRole(Role role) {
        roleDao.insertRole(role);
        return role;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager",key = "'redis_role_' + #role.id")
    public Role update(Role role) {
        roleDao.update(role);
        return role;
    }


    /*
        在缓存中删除键
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @CacheEvict(value = "redisCacheManager",key = "'redis_role_' + #id")
    public int delete(int id) {
        return roleDao.delete(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    //先判断缓存中是否存在，不存在从数据库中读取之后在更新缓存
    @Cacheable(value = "redisCacheManager",key = "'redis_role_' + #id")
    public Role getRole(int id) {
        return roleDao.getRole(id);
    }

    public List<Role> findRoles(String roleName) {
        return roleDao.findRoles(roleName);
    }
}
