package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static final String ALTER = "alter table";
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("login"),
            properties.getProperty("password")
        );
    }

    private void execute(String sql) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = "create table if not exists " + tableName;
        execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "drop table if exists " + tableName;
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type)
            throws SQLException {
       String sql = ALTER + " " + tableName + " add column " + columnName + " " + type;
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName)
            throws SQLException {
        String sql = ALTER + " " + tableName + " drop column " + columnName;
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        String sql = ALTER + " " + tableName + " rename column "
                + columnName + " to " + newColumnName;
        execute(sql);
    }

    public static String getTableScheme(Connection connection, String tableName)
            throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
}