package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import java.util.Arrays;

public class Car {
    private final boolean registered;
    private final Person owner;
    private final Person[] users;
    private final String carNumber;
    private final int registrationYear;
    private static final String carJson = "{\n" +
            "  \"registered\": false,\n" +
            "  \"owner\": {\n" +
            "    \"name\": \"Bob\",\n" +
            "    \"sex\": false,\n" +
            "    \"age\": 30,\n" +
            "    \"contact\": {\n" +
            "      \"zipCode\": 123,\n" +
            "      \"phone\": \"11-111\"\n" +
            "    },\n" +
            "    \"statuses\": [\n" +
            "      \"Worker\",\n" +
            "      \"Married\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"users\": [\n" +
            "    {\n" +
            "      \"name\": \"User1\",\n" +
            "      \"sex\": false,\n" +
            "      \"age\": 90,\n" +
            "      \"contact\": {\n" +
            "        \"zipCode\": 123,\n" +
            "        \"phone\": \"11-112\"\n" +
            "      },\n" +
            "      \"statuses\": [\n" +
            "        \"Worker1\",\n" +
            "        \"Married\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"User3\",\n" +
            "      \"sex\": false,\n" +
            "      \"age\": 56,\n" +
            "      \"contact\": {\n" +
            "        \"zipCode\": 123,\n" +
            "        \"phone\": \"11-114\"\n" +
            "      },\n" +
            "      \"statuses\": [\n" +
            "        \"Worker3\",\n" +
            "        \"Married\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"carNumber\": \"123ASD\",\n" +
            "  \"registrationYear\": 2018\n" +
            "}";

    public static void main(String[] args) {
        final Person person = new Person("Bob", false, 30, new Contact("11-111"), "Worker", "Married");
        final Person person1 = new Person("User1", false, 90, new Contact("11-112"), "Worker1", "Married");
        final Person person2 = new Person("User2", false, 40, new Contact("11-113"), "Worker2", "Married");
        final Person person3 = new Person("User3", false, 56, new Contact("11-114"), "Worker3", "Married");
        Person[] users = new Person[]{person1, person2, person3};
        Car car = new Car(false, person, users, "123ASD", 2018);
        /*createJSON(car);
        jsonToObject(Car.carJson);*/

        JSONObject jsonObjectFromString = new JSONObject(carJson);
        System.out.println(jsonObjectFromString);

        JSONObject jsonObjectWithPut = new JSONObject();
        jsonObjectWithPut.put("owner",car.getOwner());
        jsonObjectWithPut.put("carNumber",car.getCarNumber());
        jsonObjectWithPut.put("registered",car.isRegistered());
        jsonObjectWithPut.put("users",car.getUsers());
        jsonObjectWithPut.put("registrationYear",car.getRegistrationYear());
        System.out.println(jsonObjectWithPut);
    }

    private static void jsonToObject(String s) {
        final Gson gson = new GsonBuilder().create();
        Car person = gson.fromJson(s, Car.class);
        System.out.println(person);
    }

    private static void createJSON(Car car) {
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
    }

    public Car(boolean registered, Person owner, Person[] users, String carNumber, int registrationYear) {
        this.registered = registered;
        this.owner = owner;
        this.users = users;
        this.carNumber = carNumber;
        this.registrationYear = registrationYear;
    }

    @Override
    public String toString() {
        return "{" +
                "registerad=" + registered +
                ", owner=" + owner +
                ", users=" + Arrays.toString(users) +
                ", carNumber='" + carNumber + '\'' +
                ", registrationYear=" + registrationYear +
                '}';
    }

    public boolean isRegistered() {
        return registered;
    }

    public Person getOwner() {
        return owner;
    }

    public Person[] getUsers() {
        return users;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @JSONPropertyIgnore
    public int getRegistrationYear() {
        return registrationYear;
    }

    public static String getCarJson() {
        return carJson;
    }
}
