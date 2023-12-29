package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String[]> rsl;
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            rsl = reader.lines()
                    .map(e -> e.split(" ", 2))
                    .toList();
            StringBuilder builder = new StringBuilder();
            boolean available = "200".equals(rsl.get(0)[0])
                    || "300".equals(rsl.get(0)[0]);
            for (String[] value : rsl) {
                if (available && ("400".equals(value[0]) || "500".equals(value[0]))) {
                    builder.append(value[1]).append(";");
                    available = false;
                }
                if (!available && ("200".equals(value[0]) || "300".equals(value[0]))) {
                    builder.append(value[1]).append(";");
                    result.add(builder.toString());
                    builder.setLength(0);
                    available = true;
                }
            }

            for (String value : result) {
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
