package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines()
                    .filter(this::ignoreCommentsAndEmptyLines)
                    .forEach(this::put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void put(String line) {
        String[] lineParts = line.split("=");
        values.put(lineParts[0], lineParts[1]);
    }

    private boolean ignoreCommentsAndEmptyLines(String line) {
        return !line.equals("") && !line.startsWith("#") && !line.equals(System.lineSeparator());
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}