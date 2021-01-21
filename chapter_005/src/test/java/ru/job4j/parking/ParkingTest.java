package ru.job4j.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.CarType;
import ru.job4j.parking.car.PassengerCar;
import ru.job4j.parking.car.Truck;
import ru.job4j.parking.lot.Lot;
import ru.job4j.parking.lot.MixedLot;
import ru.job4j.parking.lot.ParkingLot;
import ru.job4j.parking.lot.PassengerCarLot;
import ru.job4j.parking.places.ParkingPlaces;
import ru.job4j.parking.places.ShoppingCentreParkingPlaces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ParkingTest {
    List<ParkingLot> passengerLots = new ArrayList<>();
    List<ParkingLot> mixedLots = new ArrayList<>();

    @Before
    public void createPassengerLots() {
        for (int i = 0; i < 10; i++) {
            passengerLots.add(new PassengerCarLot(true));
            mixedLots.add(new MixedLot(true));
        }
    }

    @Test
    public void checkLotSize(){
        assertThat(mixedLots.get(0).lotSize(),is(2));
        assertThat(passengerLots.get(0).lotSize(),is(1));
    }

    @Test
    public void availablePassengerParkingTest() {
        ParkingPlaces parkingPlaces = new ShoppingCentreParkingPlaces("floor0", passengerLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(parkingPlaces);
        Parking parking = new ShoppingCenterParking(places);
        for (int i = 0; i < 5; i++) {
            passengerLots.get(i).parkCar(new PassengerCar());
            assertThat(passengerLots.get(i).parkedCarsTakePlace(), is(1));
        }
        for (int i = 5; i < parking.parkingPlaces().size(); i++) {
            passengerLots.get(i).parkCar(new PassengerCar());
            assertThat(passengerLots.get(i).parkedCarsTakePlace(), is(0));
        }
        System.out.println(parking.getPlaces(0).availableParkingLots().size());
        assertThat(5, is(parking.getPlaces(0).availableParkingLots().size()));
        for (ParkingPlaces p : parking.parkingPlaces()) {
            for (ParkingLot l : p.allParkingLots()) {
                ((Lot) l).setAvailable(true);
            }
        }
        assertThat(10, is(parking.getPlaces(0).availableParkingLots().size()));
    }

    @Test(expected = RuntimeException.class)
    public void parkTruckToPassengerParkingTest() {
        for (int i = 0; i < 5; i++) {
            passengerLots.get(i).parkCar(new Truck());
        }
    }

    @Test(expected = RuntimeException.class)
    public void parkCarOnUnAvailablePlace() {
        mixedLots.get(0).parkCar(new Truck());
        assertThat(mixedLots.get(0).parkedCarsTakePlace(), is(2));
        mixedLots.get(0).parkCar(new PassengerCar());
    }

    @Test(expected = RuntimeException.class)
    public void parkTruckWhenNotEnoughSpace() {
        ParkingPlaces parkingPlaces = new ShoppingCentreParkingPlaces("floor0", mixedLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(parkingPlaces);
        for (int i = 10; i < mixedLots.size() - 1; i++) {
            mixedLots.get(i).parkCar(new PassengerCar());
            assertThat(mixedLots.get(i).parkedCarsTakePlace(), is(1));
        }
        mixedLots.get(0).parkCar(new PassengerCar());
        assertThat(mixedLots.get(0).parkedCarsTakePlace(), is(1));
        mixedLots.get(0).parkCar(new Truck());
    }

    @Test
    public void parkedCarsTest() {
        mixedLots.get(0).parkCar(new PassengerCar());
        mixedLots.get(0).parkCar(new PassengerCar());
        assertThat(mixedLots.get(0).parkedCars().size(), is(2));
        assertThat(new ArrayList<Car>(mixedLots.get(0).parkedCars()), is(mixedLots.get(0).parkedCars()));
    }

    @Test
    public void parkingPlaceTest() {
        ParkingPlaces floor1 = new ShoppingCentreParkingPlaces("floor1", mixedLots);
        ParkingPlaces floor2 = new ShoppingCentreParkingPlaces("floor2", passengerLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(floor1);
        places.add(floor2);
        Parking parking = new ShoppingCenterParking(places);
        assertThat(parking.getPlaces(0).availableFor(), is(new HashSet<CarType>(List.of(CarType.PASSENGER_CAR, CarType.TRUCK))));
        assertThat(parking.getPlaces(1).availableFor(), is(new HashSet<CarType>(List.of(CarType.PASSENGER_CAR))));
    }

    @Test
    public void availableLotTest() {
        ParkingPlaces floor1 = new ShoppingCentreParkingPlaces("floor1", mixedLots);
        ParkingPlaces floor2 = new ShoppingCentreParkingPlaces("floor2", passengerLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(floor1);
        places.add(floor2);
        Parking parking = new ShoppingCenterParking(places);
        assertThat(parking.getPlaces(1).availableParkingLots().size(), is(10));
        for (ParkingLot l : parking.getPlaces(1).allParkingLots()) {
            l.parkCar(new PassengerCar());
        }
        assertThat(parking.getPlaces(1).availableParkingLots().size(), is(0));

        assertThat(parking.getPlaces(0).availableParkingLots().size(), is(10));
        for (int i = 0; i < 3; i++) {
            parking.getPlaces(0).getLot(i).parkCar(new Truck());
        }
        for (int i = 4; i < parking.getPlaces(0).allParkingLots().size(); i++) {
            parking.getPlaces(0).getLot(i).parkCar(new PassengerCar());
        }
        assertThat(parking.getPlaces(0).availableParkingLots().size(), is(7));

    }

}