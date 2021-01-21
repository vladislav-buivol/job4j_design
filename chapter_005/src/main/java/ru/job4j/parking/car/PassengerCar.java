package ru.job4j.parking.car;

public class PassengerCar implements Car {
    private static final CarType TYPE = CarType.PASSENGER_CAR;

    @Override
    public CarType type() {
        return TYPE;
    }

    @Override
    public String toString() {
        return TYPE.toString();
    }

}
