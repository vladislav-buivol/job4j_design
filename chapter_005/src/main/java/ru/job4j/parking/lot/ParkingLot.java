package ru.job4j.parking.lot;

import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.CarType;

import java.util.Collection;
import java.util.Collections;

public interface ParkingLot {
    /**
     * @return true, is car can be parked on this lot
     */
    boolean isAvailable();

    /**
     * @return carTypes that can be parked
     */
    Collection<CarType> suitableFor();

    /**
     * @return how much space take parked car
     */
    int parkedCarsTakePlace();

    /**
     * @param car - car that parked on this lot
     */
    void parkCar(Car car);

    /**
     * @return parked car on lot
     */
    Collection<Car> parkedCars();

    /**
     * @return true, if car can be parked
     */
    boolean canPark(Car car);


    /**
     * @return max allowed car size for this lot
     */
    int lotSize();

}
