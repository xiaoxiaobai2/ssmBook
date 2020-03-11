package com.zhanghao.service;

import com.zhanghao.pojo.UserRedPacket;

public interface UserRedPacketService {
    int grapRedPacket(int redPacketId,int userId);
    int grapRedPacketForVersion(int redPacketId,int userId);
    int grapRedPacketForVersion2(int redPacketId,int userId);
    int grapRedPacketForVersion3(int redPacketId,int userId);
    int grapRedPacketByRedis(int redPacketId,int userId);
}
