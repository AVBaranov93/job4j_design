package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> paths = new LinkedHashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(Files.size(file), file.getFileName().toString());
        paths.computeIfAbsent(property, e -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }

    public List<Path> findDuplicates() {
        return paths.entrySet().stream().
                filter(e -> e.getValue().size() > 1).
                map(Map.Entry::getValue).
                flatMap(Collection::stream).
                toList();
    }
}