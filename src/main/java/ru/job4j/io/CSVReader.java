package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<List<String>> input = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            while (scanner.hasNext()) {
                input.add(Arrays.stream(scanner.nextLine().split(argsName.get("delimiter"))).toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> result = filter(input, argsName.get("filter").split(","));
        List<String> out = new ArrayList<>();
        for (List<String> ls : result) {
            StringBuilder builder = new StringBuilder();
            for (String value : ls) {
                builder.append(value).append(argsName.get("delimiter"));
            }
            builder.setLength(builder.length() - 1);
            out.add(builder.toString());
        }

        String target = argsName.get("out");
        if ("stdout".equals(target)) {
            for (String value : out) {
                System.out.println(value);
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(target))) {
                for (String value : out) {
                    writer.println(value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<List<String>> filter(List<List<String>> source, String[] filter) {
        List<String> keys = source.get(0);
        List<Integer> indexToAdd = new ArrayList<>();
        for (String key : filter) {
            if (keys.contains(key)) {
                indexToAdd.add(keys.indexOf(key));
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> ls : source) {
            List<String> rsl = new ArrayList<>();
            for (Integer index : indexToAdd) {
                rsl.add(ls.get(index));
            }
            result.add(rsl);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("wrong amount of entry parameters");
        }
        if (!args[0].endsWith(".csv")) {
            throw new IllegalArgumentException("source file must have '.csv' format");
        }
        if (!(args[2].endsWith(".csv") || "stdout".equals(args[2].split("=")[args[2].split("=").length - 1]))) {
            throw new IllegalArgumentException("dest file must be 'stdout' or have a '.csv' format");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
