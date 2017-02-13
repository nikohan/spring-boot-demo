package com.test.demo.algorithms.load;

import java.util.HashMap;

/**
 * Created on 2017/2/13.
 */
public class IpMap   {
    // 待路由的Ip列表，Key代表Ip，Value代表该Ip的权重
    public static HashMap<String, Server> serverMap = new HashMap<>();

    static
    {
        Server server1 = new Server("192.168.1.100", 1);
        Server server2 = new Server("192.168.1.101", 2);
        Server server3 = new Server("192.168.1.102", 4);

        serverMap.put(server1.getIp(), server1);
        serverMap.put(server2.getIp(), server2);
        serverMap.put(server3.getIp(), server3);
    }
}
