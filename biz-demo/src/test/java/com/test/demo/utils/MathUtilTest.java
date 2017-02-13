package com.test.demo.utils;

import org.junit.Test;

/**
 * Created on 2017/2/13.
 */
public class MathUtilTest {

    @Test
    public void gcdTest() throws Exception {
        int[] s = {2,8,6};
        System.out.println(MathUtil.gcd(s));
    }
}
