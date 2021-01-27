package ru.job4j.parking.lot;

import ru.job4j.parking.car.Vehicle;

import java.util.Collection;

public interface ParkingLot {
    /**
     * @return true, is car can be parked on this lot
     */
    boolean isAvailable();

    /**
     * @return how much space take parked car
     */
    int parkedCarsTakePlace();

    /**
     * @param vehicle - car that parked on this lot
     */
    void parkCar(Vehicle vehicle);

    /**
     * @return parked car on lot
     */
    Collection<Vehicle> parkedCars();

    /**
     * @return true, if car can be parked
     */
    boolean canPark(Vehicle vehicle);


    /**
     * @return max allowed car size for this lot
     */
    int lotSize();

}
