package ru.job4j.ood.isp.my.examples.violation.example3;

public class Plane implements Vehicle {
    @Override
    public void drive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fly() {

    }

    @Override
    public void swim() {
        throw new UnsupportedOperationException();   }
}
