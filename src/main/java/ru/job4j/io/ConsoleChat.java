package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        String userAnswer = null;
        String botAnswer;
        while (!OUT.equals(userAnswer)) {
            Scanner scanner = new Scanner(System.in);
            userAnswer = scanner.next();
            if (STOP.equals(userAnswer)) {
                while (!CONTINUE.equals(userAnswer)) {
                    userAnswer = scanner.next();
                }
            }
            botAnswer = answers.get((int) (Math.random() * (answers.size() - 1)));
            log.add(userAnswer);
            log.add(botAnswer);
            System.out.println(botAnswer);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            while (reader.ready()) {
                answers.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/target.txt", "./data/source.txt");
        consoleChat.run();
    }
}
