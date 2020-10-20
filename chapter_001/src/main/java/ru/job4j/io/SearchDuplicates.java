package ru.job4j.io;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicates implements FileVisitor<Path> {

    private final HashMap<File, ArrayList<Path>> paths = new HashMap<>();
    private final ArrayList<File> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File viewedFile = new File(file.getFileName(),file,FileUtils.sizeOf(file.toFile()));
        if (paths.get(viewedFile) == null){
            ArrayList<Path> filePath = new ArrayList<>();
            filePath.add(file.toAbsolutePath());
            paths.put(viewedFile, filePath);
        }
        else {
            paths.get(viewedFile).add(file);
            if(!duplicates.contains(viewedFile)){
                duplicates.add(viewedFile);
            }
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public ArrayList<File> duplicates() {
        return duplicates;
    }
}
