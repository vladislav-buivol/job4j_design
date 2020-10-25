package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;

public class ArgZip {
    private final String[] args;
    private final HashMap<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        parse();
    }

    private void addArgs(String s) {
        String[] parts = s.split("=");
        values.put(parts[0].substring(1), parts[1]);
    }

    public boolean valid() {
        for (String arg : args) {
            if (!arg.startsWith("-") || !arg.contains("=")) {
                throw new IllegalArgumentException();
            }
        }
        return true;
    }

    public String directory() {
        return values.get(Arguments.DIRECTORY);
    }

    public String exclude() {
        return values.get(Arguments.EXCLUDE);
    }

    public String output() {
        return values.get(Arguments.OUTPUT);
    }

    private void parse() {
        valid();
        Arrays.stream(args)
                .forEach(this::addArgs);
    }

    private static class Arguments {
        private static final String DIRECTORY = "d";
        private static final String EXCLUDE = "e";
        private static final String OUTPUT = "o";
    }
}