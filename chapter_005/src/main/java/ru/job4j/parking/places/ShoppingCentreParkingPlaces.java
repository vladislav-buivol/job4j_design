package ru.job4j.parking.places;

import ru.job4j.parking.lot.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCentreParkingPlaces implements ParkingPlaces {
    private final List<ParkingLot> parkingLots;
    private String placeName;

    public ShoppingCentreParkingPlaces(String placeName, List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.placeName = placeName;
    }

    @Override
    public List<ParkingLot> allParkingLots() {
        return parkingLots;
    }

    @Override
    public List<ParkingLot> availableParkingLots() {
        List<ParkingLot> availableLots = new ArrayList<>();
        for (ParkingLot lot : parkingLots) {
            if (lot.isAvailable()) {
                availableLots.add(lot);
            }
        }
        return availableLots;
    }

    @Override
    public ParkingLot getLot(int i) {
        return parkingLots.get(i);
    }

    @Override
    public String toString() {
        return "ShoppingCentreParkingPlaces{"
                + "parkingLots=" + parkingLots
                + ", placeName='" + placeName + '\'' + '}';
    }
}
