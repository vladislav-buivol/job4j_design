package ru.job4j.parking.places;

import ru.job4j.parking.car.VehicleType;
import ru.job4j.parking.lot.ParkingLot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCentreParkingPlaces implements ParkingPlaces {
    private final Set<VehicleType> suitableCars = new HashSet<>();
    private final List<ParkingLot> parkingLots;

    public ShoppingCentreParkingPlaces(String placeName, List<ParkingLot> parkingLots) {
        for (ParkingLot lot : parkingLots) {
            if (lot.suitableFor() == null) {
                throw new RuntimeException(String.format("Suitable car is null for %s", lot));
            }
            this.suitableCars.addAll(lot.suitableFor());
        }
        this.parkingLots = parkingLots;
    }

    @Override
    public Set<VehicleType> availableFor() {
        return suitableCars;
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
                + "suitableCars=" + suitableCars
                + ", parkingLots=" + parkingLots
                + '}';
    }
}
