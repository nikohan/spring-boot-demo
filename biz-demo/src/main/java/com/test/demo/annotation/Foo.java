package com.test.demo.annotation;

import java.lang.reflect.Method;

/**
 * Created by zhaohan on 2016/8/4.
 */
public class Foo {

    @TestAnnotation
    public void test1() {
        System.out.println("test1 annotation");
    }

    @TestAnnotation
    public static void test2() {
        System.out.println("test2 annotation");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        int passed = 0;
        int failed = 0;
        Class clazz = Class.forName("com.test.demo.annotation.Foo");
        for (Method method : clazz.getMethods()) {
            if(method.isAnnotationPresent(TestAnnotation.class)) {
                try {
                    method.invoke(null);
                    passed ++;
                } catch (Exception  ex) {
                    System.out.printf("Test %s failed: %s %n", method, ex.getCause());
                    failed ++;
                    ex.printStackTrace();
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}
