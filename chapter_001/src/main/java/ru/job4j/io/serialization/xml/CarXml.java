package ru.job4j.io.serialization.xml;

import ru.job4j.io.Serializator;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarXml {
    @XmlAttribute
    private boolean registered;


    private Person owner;

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private Person[] users;

    @XmlAttribute
    private String carNumber;

    @XmlAttribute
    private int registrationYear;


    public static void main(String[] args) {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        Person person2 = new Person(true, 44, new Contact("22-22"), "Worker", "Married");
        CarXml car = new CarXml(true, person, new Person[]{person, person2}, "123asd", 2020);
        Serializator serializator = new Serializator();
        System.out.println(serializator.serialize(car, CarXml.class));
        Reader fileReader = new Reader();
        String carXML = fileReader.read("chapter_001/src/main/java/ru/job4j/io/serialization/xml/cars/car.xml");
        System.out.println(serializator.deserialize(carXML, CarXml.class));
    }


    @Override
    public String toString() {
        return "CarXml{"
                + "registered=" + registered
                + ", owner=" + owner
                + ", users=" + Arrays.toString(users)
                + ", carNumber='" + carNumber + '\''
                + ", registrationYear=" + registrationYear
                + '}';
    }

    public CarXml(boolean registered, Person owner, Person[] users, String carNumber, int registrationYear) {
        this.registered = registered;
        this.owner = owner;
        this.users = users;
        this.carNumber = carNumber;
        this.registrationYear = registrationYear;
    }

    public CarXml() {
    }

}
