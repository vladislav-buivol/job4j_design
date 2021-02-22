package ru.job4j.io.serialization.xml;

import java.util.Arrays;

public class PersonXML {

    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public static void main(String[] args) {
        final PersonXML person = new PersonXML(false, 30, new Contact("11-111"), "Worker", "Married");

    }

    public PersonXML(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}