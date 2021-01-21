package ru.job4j.parking;

import ru.job4j.parking.places.ParkingPlaces;

import java.util.List;

public class ShoppingCenterParking implements Parking {
    private final List<ParkingPlaces> parkingPlaces;

    public ShoppingCenterParking(List<ParkingPlaces> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    @Override
    public List<ParkingPlaces> parkingPlaces() {
        return parkingPlaces;
    }

    @Override
    public ParkingPlaces getPlaces(int i) {
        return parkingPlaces.get(i);
    }

    @Override
    public String toString() {
        return "ShoppingCenterParking{"
                + "parkingPlaces=" + parkingPlaces
                + '}';
    }
}
