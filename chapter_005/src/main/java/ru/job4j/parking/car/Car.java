package ru.job4j.parking.car;

public class Car implements Vehicle {
    private final VehicleType type;
    private final int requiredSpace;

    public Car(int requiredSpace, VehicleType type) {
        this.type = type;
        if ((type.getMinRequiredSpace() != null && requiredSpace < type.getMinRequiredSpace())
                || type.getMaxRequiredSpace() != null && requiredSpace > type.getMaxRequiredSpace()) {
            throw new RuntimeException(String.format("Required space for %s in rage %s to %s but was %s", type, type.getMinRequiredSpace(), type.getMaxRequiredSpace(), requiredSpace));
        }
        this.requiredSpace = requiredSpace;
    }

    @Override
    public int getRequiredSpace() {
        return requiredSpace;
    }

    @Override
    public VehicleType type() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
