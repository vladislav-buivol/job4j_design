package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void write(String fileName, String line) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}