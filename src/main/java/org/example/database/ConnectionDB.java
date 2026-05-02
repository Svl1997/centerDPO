package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    // Параметры подключения – ИЗМЕНИТЕ НА СВОИ!
    private static final String URL = "jdbc:postgresql://localhost:5432/dpo_ryazantsev";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ssonikk1";

    public static Connection getConnection() throws SQLException {
        try {
            // Регистрация драйвера (в современных версиях Java необязательно, но для надёжности)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Драйвер PostgreSQL не найден", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
