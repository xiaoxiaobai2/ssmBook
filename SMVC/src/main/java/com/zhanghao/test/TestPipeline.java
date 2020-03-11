package com.zhanghao.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * pipline   管道，获取更快速
 */
public class TestPipeline {
    public static void main(String[] args) {
        Jedis jedis = JedisUtils.getJedis();
        Pipeline pipelined = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipelined.set("ke1"+i,i+"");
        }
        long end = System.currentTimeMillis();
        System.out.println("end-start = " + (end - start));
    }
}
