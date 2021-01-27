package ru.job4j.ood.isp.my.examples.violation.example3;

public class Car implements Vehicle {
    @Override
    public void drive() {

    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void swim() {
        throw new UnsupportedOperationException();
    }
}
