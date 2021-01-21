package ru.job4j.parking.lot;

import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.CarType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Lot implements ParkingLot {
    protected boolean available;
    protected int parkingLotSize;
    protected int remainingSpace;
    protected List<Car> parkedCars = new ArrayList<>();


    public Lot(int parkingLotSize, Car parkedCar) {
        if (parkedCar == null) {
            throw new RuntimeException("Parked car cannot be null");
        }
        this.available = false;
        this.parkedCars.add(parkedCar);
        this.parkingLotSize = parkingLotSize;
        this.remainingSpace = parkingLotSize - parkedCar.type().getRequiredSpace();
    }

    public Lot(boolean available, int parkingLotSize) {
        this.available = available;
        this.parkingLotSize = parkingLotSize;
        this.remainingSpace = parkingLotSize;
    }

    public void setAvailable(boolean available) {
        if (available) {
            this.parkedCars.clear();
        }
        this.available = available;
    }

    @Override
    public void parkCar(Car car) {
        if (!canPark(car)) {
            throw new RuntimeException(String.format("Car: %s cannot be parked here", car));
        } else {
            int remaining = remainingSpace - car.type().getRequiredSpace();
            if (remaining >= 0) {
                this.parkedCars.add(car);
                if (remaining == 0) {
                    this.setAvailable(false);
                }
            } else {
                throw new RuntimeException(String.format("Car: %s cannot be parked here", car));
            }
            this.remainingSpace = remaining;
        }
    }

    @Override
    public boolean canPark(Car car) {
        return suitableFor().contains(car.type()) || isAvailable();
    }

    @Override
    public abstract Collection<CarType> suitableFor();

    @Override
    public abstract boolean isAvailable();

    @Override
    public int parkedCarsTakePlace() {
        if (parkedCars.size() == 0) {
            return 0;
        }
        int i = 0;
        for (Car car : parkedCars) {
            i += car.type().getRequiredSpace();
        }
        return i;
    }

    @Override
    public int lotSize() {
        return this.parkingLotSize;
    }

    @Override
    public String toString() {
        return "Lot{" + "available=" + available + ", parkingLotSize=" + parkingLotSize + ", remainingSpace=" + remainingSpace + ", parkedCars=" + parkedCars + '}';
    }
}
