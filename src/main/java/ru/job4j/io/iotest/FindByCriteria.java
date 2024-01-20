package ru.job4j.io.iotest;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FindByCriteria {

    public static void execute(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validateInout(argsName);
        List<Path> paths = findByCriteria(argsName);
        try (FileWriter writer = new FileWriter(argsName.get("o"))) {
            for (Path rsl : paths) {
                writer.write(rsl + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> findByCriteria(ArgsName argsName) throws IOException {
        List<Path> files = new ArrayList<>();
        if ("name".equals(argsName.get("t"))) {
            files = findByName(Path.of(argsName.get("d")), argsName.get("n"));
        } else if ("regex".equals(argsName.get("t"))) {
            files = findByRegex(Path.of(argsName.get("d")), argsName.get("n"));
        } else if ("mask".equals(argsName.get("t"))) {
            files = findByMask(Path.of(argsName.get("d")), argsName.get("n"));
        }
        return files;
    }

    private static List<Path> findByRegex(Path root,  String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        return Search.search(root, path -> pattern.matcher(path.toFile().getName()).find());
    }

    private static List<Path> findByName(Path root,  String name) throws IOException {
        return Search.search(root, path -> path.toFile().getName().endsWith(name));
    }

    private static List<Path> findByMask(Path root, String mask) throws IOException {
        return findByRegex(root, mask.replace(".", "\\.")
                .replace("*", ".*").replace("?", "."));
    }

    private static void validateInout(ArgsName argsName) {
        Path path = Path.of(argsName.get("d"));
        String mask = argsName.get("n");
        String typeOfSearch = argsName.get("t");
        String destFile = argsName.get("o");
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Not exist %s", path.toAbsolutePath()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory %s", path.toAbsolutePath()));
        }
        if (!"name".equals(typeOfSearch) && !"mask".equals(typeOfSearch) && !"regex".equals(typeOfSearch)) {
            throw new IllegalArgumentException("wrong third parameter. Use one of the 3 following:\n"
                    + "1. name\n2. mask\n3. regex");
        }
        if (destFile == null) {
            throw new IllegalArgumentException("specify file to write the result");
        }
        if (mask == null) {
            throw new IllegalArgumentException("specify \"name\", \"mask\" or \"regex\" to search files");
        }
    }

    public static void main(String[] args) throws IOException {
        FindByCriteria.execute(args);
    }
}