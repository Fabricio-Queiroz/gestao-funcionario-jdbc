package com.example.gestao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DEFAULT_URL =
            "jdbc:sqlserver://localhost:1433;databaseName=empresa_db;encrypt=true;trustServerCertificate=true";
    private static final String DEFAULT_USER = "sa";
    private static final String DEFAULT_PASSWORD = "LocalDev123!";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        String url = envOrDefault("DB_URL", DEFAULT_URL);
        String user = envOrDefault("DB_USER", DEFAULT_USER);
        String password = envOrDefault("DB_PASSWORD", DEFAULT_PASSWORD);

        return DriverManager.getConnection(url, user, password);
    }

    private static String envOrDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
