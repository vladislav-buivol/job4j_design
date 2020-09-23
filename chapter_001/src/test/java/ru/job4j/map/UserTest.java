package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class UserTest {
    @Test
    public void createUserTest() {

        Calendar calendar = new GregorianCalendar(2013, Calendar.FEBRUARY, 31);
        User user1 = new User("User", 1, calendar);
        User user2 = new User("User", 1, calendar);

        HashMap<User, Object> map = new HashMap<>();
        map.put(user1, "user1");
        map.put(user2, "user2");

        for (User user : map.keySet()) {
            System.out.println(String.format("key: %s, value: %s", user, map.get(user)));
        }

    }
}
