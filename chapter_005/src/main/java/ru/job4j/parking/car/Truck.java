package ru.job4j.parking.car;

public class Truck implements Vehicle {
    private final int requiredSpace;

    public Truck(int requiredSpace) {
        if (requiredSpace <= 1) {
            throw new RuntimeException(String.format("Minimal required space truck is 2, but was %s", requiredSpace));
        }
        this.requiredSpace = requiredSpace;
    }

    @Override
    public int getRequiredSpace() {
        return requiredSpace;
    }
}
