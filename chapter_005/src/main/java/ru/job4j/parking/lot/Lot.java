package ru.job4j.parking.lot;

import ru.job4j.parking.car.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Lot implements ParkingLot {
    protected boolean available;
    protected int maxAllowedCarSize;
    protected int remainingSpace;
    protected List<Vehicle> parkedVehicles = new ArrayList<>();


    public Lot(boolean available, int maxAllowedCarSize) {
        this.available = available;
        this.maxAllowedCarSize = maxAllowedCarSize;
        this.remainingSpace = maxAllowedCarSize;
    }

    public void setAvailable(boolean available) {
        if (available) {
            this.parkedVehicles.clear();
        }
        this.available = available;
    }

    @Override
    public void parkCar(Vehicle vehicle) {
        if (!canPark(vehicle)) {
            throw new RuntimeException(String.format("Car: %s cannot be parked here", vehicle));
        } else {
            int remaining = remainingSpace - vehicle.getRequiredSpace();
            if (remaining >= 0) {
                this.parkedVehicles.add(vehicle);
                if (remaining == 0) {
                    this.setAvailable(false);
                }
            } else {
                throw new RuntimeException(String.format("Car: %s cannot be parked here", vehicle));
            }
            this.remainingSpace = remaining;
        }
    }

    @Override
    public Collection<Vehicle> parkedCars() {
        return parkedVehicles;
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        return isAvailable();
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public int parkedCarsTakePlace() {
        if (parkedVehicles.size() == 0) {
            return 0;
        }
        int i = 0;
        for (Vehicle vehicle : parkedVehicles) {
            i += vehicle.getRequiredSpace();
        }
        return i;
    }

    @Override
    public int lotSize() {
        return this.maxAllowedCarSize;
    }

    @Override
    public String toString() {
        return "Lot{" + "available=" + available + ", parkingLotSize=" + maxAllowedCarSize + ", remainingSpace=" + remainingSpace + ", parkedCars=" + parkedVehicles + '}';
    }
}
