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

    public void equalTest() {
        EqualDemo a = new EqualDemo();
        a.setA(1);
        a.setB(2);
        EqualDemo b = new EqualDemo();
        b.setA(1);
        b.setB(2);
        EqualDemo c = new EqualDemo();
        c.setA(1);
        c.setB(2);

        System.out.println(a.equals(b));
        System.out.println(b.equals(c));
        System.out.println(a.equals(c));
    }

    public void hashCodeTest() throws Exception {
        EqualDemo a = new EqualDemo();
        a.setA(1);
        a.setB(2);
        EqualDemo b = new EqualDemo();
        b.setA(1);
        b.setB(2);

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
        System.out.println(a == b);
    }

    public void equalTest2() throws Exception {
        EqualDemo a = new EqualDemo();
        a.setA(1);
        a.setB(2);
        SubEqualDemo b = new SubEqualDemo();
        b.setA(1);
        b.setB(2);

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
    }
}
