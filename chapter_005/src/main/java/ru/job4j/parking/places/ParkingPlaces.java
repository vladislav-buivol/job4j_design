package ru.job4j.parking.places;

import ru.job4j.parking.car.VehicleType;
import ru.job4j.parking.lot.ParkingLot;

import java.util.Collection;

public interface ParkingPlaces {
    /**
     * @return carTypes that can be parked in this parking lot
     */
    Collection<VehicleType> availableFor();

    /**
     * @return all parking lots
     */
    Collection<ParkingLot> allParkingLots();

    /**
     * @return number of available parking lots
     */
    Collection<ParkingLot> availableParkingLots();

    /**
     * @return specific lot
     */
    ParkingLot getLot(int i);



}
