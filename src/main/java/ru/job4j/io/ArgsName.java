package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(e -> e.split("=", 2))
                .forEach(e -> values.put(e[0].substring(1), e[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String value : args) {
            if (!value.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", value));
            }
            if (value.split("=", 2)[0].startsWith("-")
                    & value.split("=", 2)[0].length() == 1) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", value));
            }
            if ("".equals(value.split("=", 2)[1])) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", value));
            }
            if (!value.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", value));
            }
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
