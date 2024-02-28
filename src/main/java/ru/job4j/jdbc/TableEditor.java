package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        try (FileReader in = new FileReader("data/app.properties")) {
            properties.load(in);
        }
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            statement.execute(sql);
        }

    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("new_table");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.addColumn("new_table", "name", "varchar(30)");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.renameColumn("new_table", "name", "first_name");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.dropColumn("new_table", "first_name");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.dropTable("new_table");
        tableEditor.close();
    }
}
