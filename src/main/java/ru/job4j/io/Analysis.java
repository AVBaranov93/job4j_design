package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean available = true;
            while (reader.ready()) {
                String rsl = reader.readLine();
                if (available && (rsl.contains("400") || rsl.contains("500"))) {
                    writer.append(rsl.split(" ", 2)[1]).append(";");
                    available = false;
                }
                if (!available && (rsl.contains("200") || rsl.contains("300"))) {
                    writer.append(rsl.split(" ", 2)[1]).append(";").append(System.lineSeparator());
                    available = true;
                }
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
