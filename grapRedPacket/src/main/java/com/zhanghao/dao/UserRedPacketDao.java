package com.zhanghao.dao;

import com.zhanghao.pojo.UserRedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketDao {
    int grapRedPacket(UserRedPacket userRedPacket);
}
