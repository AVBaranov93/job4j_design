package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(String[] args) {
        validateInput(args);
        ArgsName argsName = ArgsName.of(args);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(argsName.get("o"))))) {
            for (Path inner : Search.search(Path.of(argsName.get("d")), e -> !e.toString().endsWith(argsName.get("e")))) {
                zip.putNextEntry(new ZipEntry(inner.toAbsolutePath().toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(inner.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateInput(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException("Source directory does not exist");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Wrong format of extension");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Wrong archive extension");
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("C:/Users/ashot/Desktop/pom.zip")
        );
        zip.packFiles(args);

    }
}
