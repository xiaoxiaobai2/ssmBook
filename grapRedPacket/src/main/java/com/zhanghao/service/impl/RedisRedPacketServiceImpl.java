package com.zhanghao.service.impl;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.zhanghao.pojo.UserRedPacket;
import com.zhanghao.service.RedPacketService;
import com.zhanghao.service.RedisRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    //每次读取100条去保存
    private static final int TIME_SIZE = 1000;

    private static final String PREFIX = "red_packet_list_";

    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private DataSource dataSource = null;

    /*
        开启一个新的线程
     */
    @Async
    public void saveUserRedPacketByRedis(int redPacketId, double unitAmount) {
        System.err.println("开始保存数据。。。");
        long start = System.currentTimeMillis();

        BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);

        long size = ops.size();
        long times = size%TIME_SIZE==0?size/TIME_SIZE:size/TIME_SIZE+1;
        int count = 0;

        ArrayList<UserRedPacket> userRedPackets = new ArrayList<UserRedPacket>(TIME_SIZE);

        for (int i = 0; i < times; i++) {
            List userIdList = null;
            if (i==0)
                userIdList = ops.range(i*TIME_SIZE,(i+1)*TIME_SIZE);
            else
                userIdList = ops.range(i*TIME_SIZE+1,(i+1)*TIME_SIZE);
            userRedPackets.clear();

            for (Object o : userIdList) {
                String args = (String) o;
                String[] split = args.split("-");
                int userId = Integer.parseInt(split[0]);
                long time = Long.parseLong(split[1]);

                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setNote("抢红包"+redPacketId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));

                userRedPackets.add(userRedPacket);
            }
            count += executeBatch(userRedPackets);
        }

        //清除数据
        redisTemplate.delete(PREFIX+redPacketId);
        long end = System.currentTimeMillis();
        System.err.println("保存数据结束，耗时 " + (end - start) +"ms,共保存"+count + "条数据");
    }

    /*
        批量存储
     */
    private int executeBatch(List<UserRedPacket> userRedPacketList){
        Connection conn = null;
        Statement statement = null;
        int[] count =null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            for (UserRedPacket userRedPacket : userRedPacketList) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String sql1 = "update t_red_packet set stock = stock-1 where id=" + userRedPacket.getRedPacketId();
                /*

                        注意SQL拼接时   非数值型数据  一定要＋  ’‘

                 */
                String sql2 = "insert into t_user_red_packet(red_packet_id,user_id,amount,grab_time,note) values("+
                        userRedPacket.getRedPacketId()+","+userRedPacket.getUserId() + "," + userRedPacket.getAmount()+
                        ",'" + df.format(userRedPacket.getGrabTime()) + "','" +userRedPacket.getNote()+"')";
                statement.addBatch(sql1);
                statement.addBatch(sql2);
            }
            //执行批量任务
            count = statement.executeBatch();
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            System.out.println("抢红包批量执行程序出错。");
            e.printStackTrace();
        }finally {
            try {
                if (conn!=null&&!conn.isClosed())
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (count==null)
            return 0;
        return count.length/2;
    }
}
