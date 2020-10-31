package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte importantData = (byte) 109;
        long id = 123143253425234L;
        String name = "Petr Arsentev";
        char gender = 'm';
        int age = 33;
        double salary = 4312523.43d;
        float weight = 77.7f;
        short favoriteNumber = 7;
        LOG.debug("User info name : {}, age : {}, ID: {}", name, age, id);
        LOG.info("User info name : {}, gender: {}", name, gender);
        LOG.error("Important data is incorrect. ID: {}, important data {}", id, importantData);
        LOG.warn("Data is incorrect. ID:{}, name:{}, salary:{}, weight:{}, favorite number:{}", id, name, salary, weight, favoriteNumber);
    }

}
