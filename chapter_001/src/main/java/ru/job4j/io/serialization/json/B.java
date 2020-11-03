package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        // without @JSONPropertyIgnore in A class there will be StackOverflowError
        System.out.println(new JSONObject(b));
    }
}