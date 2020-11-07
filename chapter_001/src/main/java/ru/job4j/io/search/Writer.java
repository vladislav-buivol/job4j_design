package ru.job4j.io.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class Writer {
    public static void write(List<Path> ls, String pathToWrite) {
        File f = new File(pathToWrite);
        deleteIfExist(f);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(f.getAbsolutePath(), StandardCharsets.UTF_8, true))) {
            ls.forEach(el -> writeLine(bf, el));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void deleteIfExist(File f) {
        if (f.exists()) {
            f.delete();
        }
    }

    private static void writeLine(BufferedWriter bf, Path el) {
        try {
            bf.write(String.format("%s%s", el, System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
