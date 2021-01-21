package ru.job4j.parking.car;

public enum CarType {
    PASSENGER_CAR(1),
    TRUCK(2);

    int requiredSpace;

    public int getRequiredSpace() {
        return requiredSpace;
    }

    CarType(int requiredSpace) {
        this.requiredSpace = requiredSpace;
    }
}
