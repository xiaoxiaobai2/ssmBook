<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/4
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>测试抢红包</title>


    <script src="js/jquery-3.3.1.js"></script>

    <script>
        $(function () {
            //模拟30000ge 异步请求
            var max =30000;
            for (var i=1;i<=max;i++){
                $.get(
                    "userRedPacket/grapRedPacket.do?redPacketId=6&userId=" +i,{},function (result) {

                    }
                );
            }
        })
    </script>
</head>


<body>

    <h1>测试高并发抢红包</h1>

</body>
</html>
