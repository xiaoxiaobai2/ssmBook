package com.zhanghao.service;

public interface RedisRedPacketService {
    void saveUserRedPacketByRedis(int redPacketId,double unitAmount);
}
