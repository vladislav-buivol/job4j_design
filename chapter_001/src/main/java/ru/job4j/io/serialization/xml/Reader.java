package ru.job4j.io.serialization.xml;

import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {

    public String read(String path) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(result::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
