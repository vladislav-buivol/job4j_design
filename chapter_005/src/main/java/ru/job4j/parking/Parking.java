package ru.job4j.parking;

import ru.job4j.parking.places.ParkingPlaces;

import java.util.Collection;

public interface Parking {
    /**
     * @return all parking places
     */
    Collection<ParkingPlaces> parkingPlaces();

    /**
     * @return specific parking place
     */
    ParkingPlaces getPlaces(int i);
}
