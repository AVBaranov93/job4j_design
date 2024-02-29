package ru.job4j.spammer;

import ru.job4j.jdbc.TableEditor;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    Connection connection;
    private String dump;

    public ImportDB(Properties config, String dump) throws Exception {
        this.config = config;
        this.dump = dump;
        initConnection();
    }

    private void initConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver"));
        connection = DriverManager.getConnection(config.getProperty("url"), config.getProperty("login"), config.getProperty("password"));
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users ("
                + "id serial primary key,"
                + "name text,"
                + "email text)"
        )) {
            preparedStatement.execute();
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .map(e -> {
                        String[] rsl = e.split(";");
                        if (rsl.length != 2 || "".equals(rsl[0]) || "".equals(rsl[1])) {
                            throw new IllegalArgumentException("wrong number of entry parameters");
                        }
                        return rsl;
                    })
                    .forEach(e -> users.add(new User(e[0], e[1])));
        }
        return users;
    }

    public void save(List<User> users) throws Exception {
        for (User user : users) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                preparedStatement.setString(1, user.name);
                preparedStatement.setString(2, user.email);
                preparedStatement.execute();
            }
        }

    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
