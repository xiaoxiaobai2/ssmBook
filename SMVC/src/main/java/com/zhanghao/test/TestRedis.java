package com.zhanghao.test;

import redis.clients.jedis.Jedis;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = JedisUtils.getJedis();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set("ke1"+i,i+"");
        }
        long end = System.currentTimeMillis();
        System.out.println("end-start = " + (end - start));
    }
}
