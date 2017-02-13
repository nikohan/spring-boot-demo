package com.test.demo.algorithms.load;

/**
 * Created on 2017/2/13.
 */
public class Server {
    private String name;
    private String ip;
    private int weight;

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
