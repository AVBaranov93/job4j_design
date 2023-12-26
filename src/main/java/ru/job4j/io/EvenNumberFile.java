package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int[] rsl = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                rsl[i] = Integer.parseInt(lines[i]);
            }
            for (Integer value : rsl) {
                if (value % 2 == 0) {
                    System.out.println(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
