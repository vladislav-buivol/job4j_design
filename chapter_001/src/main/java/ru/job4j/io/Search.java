package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateParameters(args);
        Path currentDir = Paths.get(args[0]);
        String extension = args[1];
        search(currentDir, extension).forEach(System.out::println);
        //Search.searchDuplicates(currentDir).forEach(el -> System.out.println(el.toString()));
    }

    private static void validateParameters(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder and extension are null. Use java -jar search.jar ROOT_FOLDER EXTENSION");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("Extension is null. Use java -jar search.jar ROOT_FOLDER EXTENSION");
        }
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Document> searchDuplicates(Path root) throws IOException {
        SearchDuplicates searcher = new SearchDuplicates();
        Files.walkFileTree(root, searcher);
        return searcher.duplicates();
    }
}