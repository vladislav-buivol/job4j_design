package ru.job4j.parking.lot;

import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.CarType;

import java.util.Collections;
import java.util.List;

public class PassengerCarLot extends Lot {
    private final static List<CarType> SUITABLE_CARS = List.of(CarType.PASSENGER_CAR);
    private final static int MAX_ALLOWED_CAR_SIZE = 1;

    public PassengerCarLot(boolean available) {
        super(available, MAX_ALLOWED_CAR_SIZE);
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public List<CarType> suitableFor() {
        return SUITABLE_CARS;
    }

    @Override
    public List<Car> parkedCars() {
        return Collections.unmodifiableList(this.parkedCars);
    }
}
