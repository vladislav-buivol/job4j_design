package ru.job4j.io.serialization.xml;

import ru.job4j.io.Person;

public class CarXml {
    private final boolean registered;
    private final PersonXML owner;
    private final PersonXML[] users;
    private final String carNumber;
    private final int registrationYear;
    final String carXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
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

    public CarXml(boolean registered, PersonXML owner, PersonXML[] users, String carNumber, int registrationYear) {
        this.registered = registered;
        this.owner = owner;
        this.users = users;
        this.carNumber = carNumber;
        this.registrationYear = registrationYear;
    }
}
