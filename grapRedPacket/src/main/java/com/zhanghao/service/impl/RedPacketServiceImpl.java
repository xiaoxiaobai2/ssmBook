package com.zhanghao.service.impl;

import com.zhanghao.dao.RedPacketDao;
import com.zhanghao.pojo.RedPacket;
import com.zhanghao.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    private RedPacketDao redPacketDao;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(int id) {

        return redPacketDao.getRedPacket(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(int id) {
        return redPacketDao.decreaseRedPacket(id);
    }
}
