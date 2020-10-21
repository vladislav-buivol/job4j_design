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

    private final HashMap<Document, ArrayList<Path>> paths = new HashMap<>();
    private final ArrayList<Document> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Document viewedDocument = new Document(file.getFileName(),file,FileUtils.sizeOf(file.toFile()));
        if (paths.get(viewedDocument) == null){
            ArrayList<Path> filePath = new ArrayList<>();
            filePath.add(file.toAbsolutePath());
            paths.put(viewedDocument, filePath);
        }
        else {
            paths.get(viewedDocument).add(file);
            if(!duplicates.contains(viewedDocument)){
                duplicates.add(viewedDocument);
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

    public ArrayList<Document> duplicates() {
        return duplicates;
    }
}
