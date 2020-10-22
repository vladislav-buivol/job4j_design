package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(this::isValidArgument)
                .forEach(this::putToValues);
    }

    private void putToValues(String arg) {
        String[] parts = arg.split("=");
        values.put(parts[0].substring(1), parts[1]);
    }

    private boolean isValidArgument(String arg) {
        if (arg.startsWith("-") && arg.contains("=")) {
            return true;
        }
        throw new IllegalArgumentException();
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}