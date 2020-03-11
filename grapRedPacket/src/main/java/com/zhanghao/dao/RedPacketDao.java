package com.zhanghao.dao;

import com.zhanghao.pojo.RedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketDao {
    RedPacket getRedPacket(int id);
    //数据库中加入for update,对主键实现行锁定

    RedPacket getRedPacketForUpdate(int id);
    int decreaseRedPacket(int id);


    int decreaseRedPacketForVersion(int id,int version);
}
