package ru.job4j.parking.car;

public enum VehicleType {
    PASSENGER_CAR(1, 1),
    TRUCK(2, null);

    private Integer minRequiredSpace;
    private Integer maxRequiredSpace;

    public Integer getMinRequiredSpace() {
        return minRequiredSpace;
    }

    public Integer getMaxRequiredSpace() {
        return maxRequiredSpace;
    }

    VehicleType(Integer minRequiredSpace, Integer maxRequiredSpace) {
        this.minRequiredSpace = minRequiredSpace;
        this.maxRequiredSpace = maxRequiredSpace;
    }
}
