package com.zhanghao.service;

import com.zhanghao.pojo.RedPacket;

public interface RedPacketService {
    RedPacket getRedPacket(int id);
    int decreaseRedPacket(int id);
}
