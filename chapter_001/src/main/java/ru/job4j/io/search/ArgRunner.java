package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;


//-d="C:\projects\job4j_design" -n=".jar" -m -o="log.txt"
//-d="C:\projects\job4j_design" -n=".gitignore" -f -o="log.txt"
//-d="C:\projects\job4j_design" -n="\\*.jar" -r -o="log.txt"
public class ArgRunner {
    public static void main(String[] args) {
        Arguments arguments = new Arguments(args);
        FileSearcher searcher = findFiles(arguments);
        Writer.write(searcher.getPaths(), arguments.output());
    }

    private static FileSearcher findFiles(Arguments arguments) {
        FileSearcher searcher = new FileSearcher(getPredicate(arguments));
        try {
            Files.walkFileTree(Paths.get(arguments.directory()), searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher;
    }

    private static Predicate<String> getPredicate(Arguments arguments) {
        return switch (arguments.searchBy()) {
            case MaskType -> p -> p.endsWith(arguments.name());
            case RegexType -> Pattern.compile(arguments.name()).asPredicate();
            case FullMatchType -> p -> p.equals(arguments.name());
            default -> throw new IllegalArgumentException();
        };
    }
}
