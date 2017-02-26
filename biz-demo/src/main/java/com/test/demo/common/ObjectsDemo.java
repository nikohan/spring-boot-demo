package com.test.demo.common;

import com.test.demo.domain.User;

import java.util.Date;
import java.util.Objects;

/**
 * Created by zhaohan on 2016/8/4.
 */
public class ObjectsDemo {
    public static void deepEqualsTest() {
        char c1[] = {'1', '2', '3'};
        char c2[] = {'1', '2', '3'};

        System.out.println("deepEqualsTest:" + Objects.deepEquals(c1, c2));
    }

    public static void compareTest() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        System.out.println("compareTest:"
                + Objects.compare(
                    user1,
                    user2,
                    (User o1, User o2) -> o1.getId().compareTo(o2.getId())
                )
        );
    }

    public static void requireNonNullTest() {
        User user1 = null;
        User user2 = new User();
        User result = Objects.requireNonNull(
                user1,
                ObjectsDemo::supplierTest //相当于 () -> supplierTest()
        );
        System.out.println("requireNonNullTest:" + result.toString());
    }

    public static String supplierTest() {
        return "hello";
    }


    public static void main(String[] args) {
        deepEqualsTest();
        compareTest();
        requireNonNullTest();
    }
}
