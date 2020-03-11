package com.zhanghao.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * redis工具
 */
public class JedisUtils {

    private static JedisPool pool = null;
    private static final Class<JedisUtils> LOCK = JedisUtils.class;

    private static void createConfig(){
        synchronized (LOCK) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
//        最大空闲数
            poolConfig.setMaxIdle(50);
            //设置最大连接数
            poolConfig.setMaxTotal(100);
            //等待毫秒数
            poolConfig.setMaxWaitMillis(20000);
            pool = new JedisPool(poolConfig, "localhost", 6379);
        }
    }

    public static Jedis getJedis(){
        if (pool==null)
            createConfig();
        return pool.getResource();
    }
}
