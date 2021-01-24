package ru.job4j.parking.car;

public interface Vehicle {
    /**
     * @return car type
     */
    VehicleType type();

    /**
     * @return required space for parking
     */
    int getRequiredSpace();
}
