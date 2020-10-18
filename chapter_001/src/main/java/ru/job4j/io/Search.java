package ru.job4j.io;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path currentDir = Paths.get(".");
        //search(currentDir, ".js").forEach(System.out::println);
        Search.searchDuplicates(currentDir).forEach(el -> System.out.println(String.format("Size: %s, Files: %s", el.size(), el)));
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<ArrayList<Path>> searchDuplicates(Path root) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> true);
        Files.walkFileTree(root, searcher);
        List<Path> paths = new ArrayList<>(searcher.getPaths());
        List<ArrayList<Path>> result = new ArrayList<>();
        while (paths.size() != 0){
            Path path = paths.remove(0);
            ArrayList<Path> duplicates = new ArrayList<>();
            for(Path p : paths){
                if(isDuplicates(path, p)){
                    addIfNotContains(p, duplicates);
                    addIfNotContains(path, duplicates);
                }
            }
            paths.removeAll(duplicates);
            addDuplicatesToResult(result, duplicates);
        }
        return result;
    }

    private static void addDuplicatesToResult(List<ArrayList<Path>> result, ArrayList<Path> duplicates) {
        if(duplicates.size() > 0){
            result.add(duplicates);
        }
    }

    private static boolean isDuplicates(Path path, Path p) {
        return path.getFileName().equals(p.getFileName()) && FileUtils.sizeOf(path.toFile()) == FileUtils.sizeOf(p.toFile());
    }

    private static void addIfNotContains(Path path, ArrayList<Path> duplicates) {
        if (!duplicates.contains(path)) {
            duplicates.add(path);
        }
    }
}