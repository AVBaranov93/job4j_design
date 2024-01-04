package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateInout(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateInout(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of entry parameters");
        }
        Path path = Path.of(args[0]);
        String extension = args[1];
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exist %s", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory %s", path.toAbsolutePath()));
        }
        if (!(extension.startsWith(".") && extension.length() > 1)) {
            throw new IllegalArgumentException("Wrong extension format");
        }
    }
}
