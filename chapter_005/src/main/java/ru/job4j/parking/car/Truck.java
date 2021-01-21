package ru.job4j.parking.car;

public class Truck implements Car {
    private static final CarType TYPE = CarType.TRUCK;

    @Override
    public CarType type() {
        return TYPE;
    }

    @Override
    public String toString() {
        return TYPE.toString();
    }
}
