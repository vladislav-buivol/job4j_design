package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
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


    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        Person person2 = new Person(true, 44, new Contact("22-22"), "Worker", "Married");
        CarXml car = new CarXml(true, person, new Person[]{person}, "123asd", 2020);
        JAXBContext context = JAXBContext.newInstance(CarXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        String carXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + " <car registered=\"true\" owner=\"Bob\" carNumber=\"123ASD\" registrationYear=\"2020\">"
                + "    <users>"
                + "        <person sex=\"false\" age=\"30\">"
                + "            <contact phone=\"11-111\"/>"
                + "            <statuses>"
                + "                <status>Worker</status>"
                + "                <status>Married</status>"
                + "            </statuses>"
                + "        </person>"
                + "        <person sex=\"false\" age=\"44\">"
                + "            <contact phone=\"22-222\"/>"
                + "            <statuses>"
                + "                <status>Worker</status>"
                + "                <status>Married</status>"
                + "            </statuses>"
                + "        </person>"
                + "    </users>"
                + " </car>";
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(carXML)) {
            CarXml carFromString = (CarXml) unmarshaller.unmarshal(reader);
            System.out.println(carFromString);
        }
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
