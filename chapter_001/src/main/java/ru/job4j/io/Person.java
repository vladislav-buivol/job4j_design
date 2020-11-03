package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import java.util.Arrays;

public class Person {
    private final String name;
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public static void main(String[] args) {
        final Person person = new Person("Bob", false, 30, new Contact("11-111"), "Worker", "Married");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        //System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"name\":Bob,"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }


    public Person(String name, boolean sex, int age, Contact contact, String... statuses) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", contact=" + contact +
                ", statuses=" + Arrays.toString(statuses) +
                '}';
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
    @JSONPropertyIgnore
    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }
}