package ru.job4j.emulator;

import ru.job4j.emulator.cache.Cache;

import java.io.*;
import java.util.Properties;

public class Emulator {

    private final static String CURRENT_DIR = String.format("%s/%s", System.getProperty("user.dir"), "chapter_004");
    private Cache<File, String> cache = new Cache<>() {
        @Override
        public String load(File file) {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                StringBuilder text = new StringBuilder();
                in.lines().forEach(text::append);
                cache.add(file, text.toString());
                System.out.println("Loading data...");
                return text.toString();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(String.format("Cannot read file %s", file));
            }
        }
    };

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        String propLocation = (System.getProperty("user.dir") + "\\chapter_004\\src\\main\\java\\ru\\job4j\\emulator\\emulator.properties").replace("\\", File.separator);
        EmulatorProperties properties = new EmulatorProperties(propLocation);
        System.out.println(emulator.getData(new File(String.format("%s/%s", CURRENT_DIR, properties.address()))));
        System.out.println(emulator.getData(new File(String.format("%s/%s", CURRENT_DIR, properties.name()))));
    }


    public boolean delete(File file) {
        return cache.delete(file);
    }

    public Cache<File, String> getCache() {
        return cache;
    }

    public String getData(File file) {
        return cache.get(file);
    }

    protected static class EmulatorProperties {
        private final String propertyLocation;
        private static final Properties PROPERTIES = new Properties();
        private static final String NAME = "name";
        private static final String ADDRESS = "address";

        public EmulatorProperties(String propertyLocation) {
            this.propertyLocation = propertyLocation;
            try {
                InputStream stream = new FileInputStream(propertyLocation);
                PROPERTIES.load(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String name() {
            return String.valueOf(PROPERTIES.get(NAME));
        }

        public String address() {
            return String.valueOf(PROPERTIES.get(ADDRESS));
        }

    }

}
