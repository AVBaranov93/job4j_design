package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .filter(e -> e.length() != 0 && !e.startsWith("#"))
                    .map(e -> e.split("=", 2))
                    .forEach(e -> {
                        if ("".equals(e[0]) || "".equals(e[1])) {
                            throw new IllegalArgumentException("illegal argument");
                        }
                        values.put(e[0], e[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> get() {
        return this.values;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
