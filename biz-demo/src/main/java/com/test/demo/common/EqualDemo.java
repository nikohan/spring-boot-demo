package com.test.demo.common;

import java.util.Objects;

/**
 * Created on 2017/6/23.
 */
public class EqualDemo {
    private int a;

    private int b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualDemo equalDemo = (EqualDemo) o;

        if (a != equalDemo.a) return false;
        return b == equalDemo.b;
    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + b;
        return result;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
