package ru.job4j.parking.car;

public class PassengerCar implements Vehicle {
    private static final int REQUIRED_SPACE = 1;

    @Override
    public int getRequiredSpace() {
        return REQUIRED_SPACE;
    }
}
