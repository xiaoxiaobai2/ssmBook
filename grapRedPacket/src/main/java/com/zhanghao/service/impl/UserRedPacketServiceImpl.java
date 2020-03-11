package com.zhanghao.service.impl;

import com.zhanghao.dao.RedPacketDao;
import com.zhanghao.dao.UserRedPacketDao;
import com.zhanghao.pojo.RedPacket;
import com.zhanghao.pojo.UserRedPacket;
import com.zhanghao.service.RedisRedPacketService;
import com.zhanghao.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private UserRedPacketDao userRedPacketDao;

    @Autowired
    private RedPacketDao redPacketDao;

    public static final int FAILED = 0;



    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacket(int redPacketId,int userId) {

//        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);

        /*
            悲观锁，利用数据库本身的 for update对主键进行行锁定
         */
        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        System.out.println("redPacket = " + redPacket);

        if (redPacket.getStock()>0){
            //减库存
            redPacketDao.decreaseRedPacket(redPacketId);

            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setNote("抢红包"+redPacketId);

            return userRedPacketDao.grapRedPacket(userRedPacket);
        }
        return FAILED;
    }


    /*
           CAS  乐观锁，解决ABA问题
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion(int redPacketId,int userId) {

        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        System.out.println("redPacket = " + redPacket);
        if (redPacket.getStock()>0){
            //减库存
            int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
            if (update==0)
                return FAILED;
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setNote("抢红包"+redPacketId);

            return userRedPacketDao.grapRedPacket(userRedPacket);
        }
        return FAILED;
    }

    /*
           CAS  乐观锁，重入机制
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion2(int redPacketId,int userId) {

        long start = System.currentTimeMillis();
        while (true){
            long end=System.currentTimeMillis();
            if (end-start>100)
                return FAILED;
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            System.out.println("redPacket = " + redPacket);
            if (redPacket.getStock()>0){
                //减库存
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
                if (update==0)
                    continue;
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setNote("抢红包"+redPacketId);

                return userRedPacketDao.grapRedPacket(userRedPacket);
            }else
                return FAILED;
        }
    }

    /*
           CAS  乐观锁，重入机制,每个最多循环3次
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion3(int redPacketId,int userId) {
        int i=3;
        while (i>0){
            i--;

            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock()>0){
                //减库存
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
                if (update==0)
                    continue;
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setNote("抢红包"+redPacketId);

                return userRedPacketDao.grapRedPacket(userRedPacket);
            }else
                return FAILED;
        }
        return FAILED;
    }


    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisRedPacketService redisRedPacketService = null;

    private String script = "local listKey='red_packet_list_'..KEYS[1] \n"+
            "local redPacket='red_packet_'..KEYS[1] \n"+
            "local stock = tonumber(redis.call('hget',redPacket,'stock')) \n"+
            "if stock<=0 then return 0 end \n"+
            "stock = stock-1\n"+
            "redis.call('hset',redPacket,'stock',tostring(stock)) \n"+
            "redis.call('rpush',listKey,ARGV[1]) \n"+
            "if stock==0 then return 2 end \n"+
            "return 1 \n";

    private String sha1=null;

    public int grapRedPacketByRedis(int redPacketId,int userId){
        String args = userId + "-" + System.currentTimeMillis();
        int result =0;
        Jedis jedis = (Jedis)redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        try {
            if (sha1==null) {
                sha1 = jedis.scriptLoad(script);
            }
            Object res = jedis.evalsha(sha1, 1, redPacketId + "", args);
            Number number = (Number)res;
            result = number.intValue();
            System.err.println("result = " + result);
            //返回结果为2，到了最后一个红包
            if (result==2){
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                System.out.println("*********************************************** ");
                String unitAmount = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId,Double.parseDouble(unitAmount));
            }
        } finally {
            if (jedis!=null && jedis.isConnected())
                jedis.close();
        }
        return result;
    }
}
