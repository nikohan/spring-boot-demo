package com.test.demo.utils;

/**
 * Created on 2017/2/13.
 */
public class MathUtil {

    //两个数的最大公约数
    public static int gcd(int a, int b) {
        int tmp;
        while(b > 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    //数组中的最大公约数
    public static int gcd(int[] s) {
        if(null == s || s.length <= 0) {
            return 0;
        }

        if(s.length == 1) {
            return s[0];
        }

        int res = s[0];

        for(int i = 1; i < s.length; i++) {
            res = gcd(res, s[i]);
        }

        return res;
    }
}
