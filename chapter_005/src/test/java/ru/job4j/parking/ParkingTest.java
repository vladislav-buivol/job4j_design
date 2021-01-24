package ru.job4j.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.parking.car.Car;
import ru.job4j.parking.car.Vehicle;
import ru.job4j.parking.car.VehicleType;
import ru.job4j.parking.lot.Lot;
import ru.job4j.parking.lot.ParkingLot;
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
            passengerLots.add(new Lot(true, 1, List.of(VehicleType.PASSENGER_CAR)));
            mixedLots.add(new Lot(true, 2, List.of(VehicleType.PASSENGER_CAR, VehicleType.TRUCK)));
        }
    }

    @Test
    public void checkLotSize() {
        assertThat(mixedLots.get(0).lotSize(), is(2));
        assertThat(passengerLots.get(0).lotSize(), is(1));
    }

    @Test
    public void availablePassengerParkingTest() {
        ParkingPlaces parkingPlaces = new ShoppingCentreParkingPlaces("floor0", passengerLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(parkingPlaces);
        Parking parking = new ShoppingCenterParking(places);
        for (int i = 0; i < 5; i++) {
            passengerLots.get(i).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
            assertThat(passengerLots.get(i).parkedCarsTakePlace(), is(1));
        }
        for (int i = 5; i < parking.parkingPlaces().size(); i++) {
            passengerLots.get(i).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
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
            passengerLots.get(i).parkCar(new Car(2, VehicleType.TRUCK));
        }
    }

    @Test(expected = RuntimeException.class)
    public void parkCarOnUnAvailablePlace() {
        mixedLots.get(0).parkCar(new Car(2, VehicleType.TRUCK));
        assertThat(mixedLots.get(0).parkedCarsTakePlace(), is(2));
        mixedLots.get(0).parkCar(new Car(2, VehicleType.TRUCK));
    }

    @Test(expected = RuntimeException.class)
    public void parkTruckWhenNotEnoughSpace() {
        ParkingPlaces parkingPlaces = new ShoppingCentreParkingPlaces("floor0", mixedLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(parkingPlaces);
        for (int i = 10; i < mixedLots.size() - 1; i++) {
            mixedLots.get(i).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
            assertThat(mixedLots.get(i).parkedCarsTakePlace(), is(1));
        }
        mixedLots.get(0).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
        assertThat(mixedLots.get(0).parkedCarsTakePlace(), is(1));
        mixedLots.get(0).parkCar(new Car(2, VehicleType.TRUCK));
    }

    @Test
    public void parkedCarsTest() {
        mixedLots.get(0).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
        mixedLots.get(0).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
        assertThat(mixedLots.get(0).parkedCars().size(), is(2));
        assertThat(new ArrayList<>(mixedLots.get(0).parkedCars()), is(mixedLots.get(0).parkedCars()));
    }

    @Test
    public void parkingPlaceTest() {
        ParkingPlaces floor1 = new ShoppingCentreParkingPlaces("floor1", mixedLots);
        ParkingPlaces floor2 = new ShoppingCentreParkingPlaces("floor2", passengerLots);
        List<ParkingPlaces> places = new ArrayList<>();
        places.add(floor1);
        places.add(floor2);
        Parking parking = new ShoppingCenterParking(places);
        assertThat(parking.getPlaces(0).availableFor(), is(new HashSet<>(List.of(VehicleType.PASSENGER_CAR, VehicleType.TRUCK))));
        assertThat(parking.getPlaces(1).availableFor(), is(new HashSet<>(List.of(VehicleType.PASSENGER_CAR))));
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
            l.parkCar(new Car(1, VehicleType.PASSENGER_CAR));
        }
        assertThat(parking.getPlaces(1).availableParkingLots().size(), is(0));

        assertThat(parking.getPlaces(0).availableParkingLots().size(), is(10));
        for (int i = 0; i < 3; i++) {
            parking.getPlaces(0).getLot(i).parkCar(new Car(2, VehicleType.TRUCK));
        }
        for (int i = 4; i < parking.getPlaces(0).allParkingLots().size(); i++) {
            parking.getPlaces(0).getLot(i).parkCar(new Car(1, VehicleType.PASSENGER_CAR));
        }
        assertThat(parking.getPlaces(0).availableParkingLots().size(), is(7));
    }

    @Test(expected = RuntimeException.class)
    public void createPassengerCarWithWrongRequiredSpace(){
        Vehicle car = new Car(2, VehicleType.PASSENGER_CAR);
    }

    @Test(expected = RuntimeException.class)
    public void createTruckCarWithWrongRequiredSpace(){
        Vehicle car = new Car(1, VehicleType.TRUCK);
    }

}