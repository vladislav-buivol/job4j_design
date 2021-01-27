package ru.job4j.ood.isp.my.examples.violation.example3;

public class Boat implements Vehicle {

    @Override
    public void drive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swim() {

    }
}
