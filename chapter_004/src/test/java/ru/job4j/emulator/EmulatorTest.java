package ru.job4j.emulator;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmulatorTest {
    private final static String CURRENT_DIR = System.getProperty("user.dir");

    @Test
    public void testCacheEmulator() {
        Emulator emulator = new Emulator();
        String propLocation = (System.getProperty("user.dir") + "\\src\\test\\java\\ru\\job4j\\emulator\\test_emulator.properties").replace("\\", File.separator);
        Emulator.EmulatorProperties properties = new Emulator.EmulatorProperties(propLocation);
        File address = new File(String.format("%s/%s", CURRENT_DIR, properties.address()));
        File name = new File(String.format("%s/%s", CURRENT_DIR, properties.name()));
        assertThat(emulator.getData(name), equalTo("names"));
        assertThat(emulator.getData(address), equalTo("address"));
        assertThat(emulator.delete(name),equalTo(true));
        assertThat(emulator.getData(name), equalTo("names"));
    }
}