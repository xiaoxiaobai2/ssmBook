package com.zhanghao.test;

import com.zhanghao.config.RedisConfig;
import com.zhanghao.config.RootConfig;
import com.zhanghao.pojo.Role;
import com.zhanghao.service.RoleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestRedis {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class, RedisConfig.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);

//        System.err.println("\n***************************************************************\n");
//        Role role = roleService.getRole(1);
//        System.out.println("role = " + role);
//        Role role5 = roleService.getRole(1);

        System.err.println("\n***************************************************************\n");
//        Role role1 = new Role();
//        role1.setId(2);
//        role1.setRoleName("update");
//        role1.setNote("update_test");
//        roleService.update(role1);
        Role role6 = roleService.getRole(2);
        System.out.println("role6 = " + role6);


        System.err.println("\n***************************************************************\n");
        Role role7 = roleService.getRole(2);
        System.out.println("role7 = " + role7);
        System.err.println("\n***************************************************************\n");
//        Role role2 = new Role();
//        role2.setRoleName("insert");
//        role2.setNote("testInsert");
//        roleService.insertRole(role2);
//        System.out.println("role2 = " + role2);


    }
}
