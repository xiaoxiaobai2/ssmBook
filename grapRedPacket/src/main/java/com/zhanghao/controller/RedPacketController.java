package com.zhanghao.controller;

import com.zhanghao.service.UserRedPacketService;
import com.zhanghao.service.impl.UserRedPacketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userRedPacket")
public class RedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping("/grapRedPacket")
    @ResponseBody
    public Map<String,Object> grapRedPacket(String redPacketId,String userId){
        System.out.println("redPacketId = " + redPacketId);

        int redPacketIdInt = Integer.parseInt(redPacketId);
        int userIdInt = Integer.parseInt(userId);
        System.out.println("userIdInt = " + userIdInt);
        System.out.println("redPacketIdInt = " + redPacketIdInt);

//        int result = userRedPacketService.grapRedPacket(redPacketIdInt,userIdInt);

        //CAS  乐观锁,解决ABA问题
//        int result = userRedPacketService.grapRedPacketForVersion(redPacketIdInt,userIdInt);

        //CAS  乐观锁，重入机制,100ms
//        int result = userRedPacketService.grapRedPacketForVersion2(redPacketIdInt,userIdInt);

        //CAS  乐观锁，重入机制,限定抢3次
//        int result = userRedPacketService.grapRedPacketForVersion2(redPacketIdInt,userIdInt);


        //redis缓存
        int result = userRedPacketService.grapRedPacketByRedis(redPacketIdInt,userIdInt);

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        boolean flag = result>0;
        System.err.println("RedPacketController.grapRedPacket");
        resultMap.put("success",flag);
        resultMap.put("message",flag?"抢红包成功":"抢红包失败");
        return resultMap;
    }
}
