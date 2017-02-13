package com.test.demo.algorithms.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 负载均衡
 * http://www.duzhi.me/article/864.html
 * Created on 2017/2/13.
 */
public class LoadDemo {
    private static Integer pos = 0;

    //轮询
    public static String getServerByRoundRobin()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos)
        {
            if (pos >= keySet.size()) {
                pos = 0;
            }
            server = keyList.get(pos);
            pos ++;
        }

        return server;
    }

    //随机
    public static String getServerByRandom()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }

    //源地址哈希（Hash）法
    public static String getServerByHash()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        // 在Web应用中可通过HttpServlet的getRemoteIp方法获取
        String remoteIp = "127.0.0.1";
        int hashCode = remoteIp.hashCode();
        int serverListSize = keyList.size();
        int serverPos = hashCode % serverListSize;

        return keyList.get(serverPos);
    }

    //加权轮询
    public static String getServerByWeightRoundRobin()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        String server = null;
        synchronized (pos)
        {
            if (pos >= keySet.size())
                pos = 0;
            server = serverList.get(pos);
            pos ++;
        }

        return server;
    }

    //加权随机
    public static String getServerByWeightRandom()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext())
        {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());

        return serverList.get(randomPos);
    }
}