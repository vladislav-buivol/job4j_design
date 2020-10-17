package ru.job4j.io;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        fileNameAndSize(file);
        printNameAndSizeOfContainingFiles(file);
    }

    private static void fileNameAndSize(File file) {
        checkIfFileExist(file);
        System.out.println(String.format("File name: %s, size: %s", file.getName(), FileUtils.sizeOf(file)));
    }

    private static void printNameAndSizeOfContainingFiles(File file) {
        checkIfFileExist(file);
        for (File subfile : file.listFiles()) {
            fileNameAndSize(subfile);
        }
    }

    private static void checkIfFileExist(File file) {
        if(file == null){
            throw new IllegalArgumentException("File is null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }
}