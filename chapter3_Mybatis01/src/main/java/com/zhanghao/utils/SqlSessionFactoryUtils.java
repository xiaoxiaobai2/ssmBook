package com.zhanghao.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static final Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;
    private static SqlSessionFactory sqlSessionFactory = null;

    private SqlSessionFactoryUtils() {
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        synchronized (LOCK) {
            if (sqlSessionFactory != null)
                return sqlSessionFactory;
            try {
                InputStream in = Resources.getResourceAsStream("Mybatis.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory==null)
            getSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}
