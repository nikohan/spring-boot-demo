package com.test.demo.common;

/**
 * Created by nikohan on 2017/1/4.
 */
public class PrimaryTypeDedmo {
    public static void changeLong(Long value) {
        value = new Long(122L);
    }

    public static void main(String[] args) {
        Long a = new Long(100000000000000L);
        changeLong(a);
        System.out.println(a);
    }
}
