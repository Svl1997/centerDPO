package org.example;

import org.example.database.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.println("✅ Подключение к базе данных успешно!");
            System.out.println("База данных: " + conn.getCatalog());
        } catch (SQLException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}