package ru.job4j.ood.ocp;

import java.util.List;

public class CarsInheritance {

    private static class Car {
        public String sound() {
            return "beep-beep";
        }
    }

    private static class NoSoundCar extends Car {
        @Override
        public String sound() {
            return "";
        }
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(new Car(), new Car(), new NoSoundCar());
        cars.forEach(Car::sound);
    }

}