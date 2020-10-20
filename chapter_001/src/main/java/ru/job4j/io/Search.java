package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path currentDir = Paths.get(".");
        //search(currentDir, ".js").forEach(System.out::println);
        Search.searchDuplicates(currentDir).forEach(el -> System.out.println(el.toString()));
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<File> searchDuplicates(Path root) throws IOException {
        SearchDuplicates searcher = new SearchDuplicates();
        Files.walkFileTree(root, searcher);
        return searcher.duplicates();
    }
}