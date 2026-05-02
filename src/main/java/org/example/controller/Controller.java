package org.example.controller;

import org.example.database.ConnectionDB;
import javax.swing.JOptionPane;  // ← добавить импорт
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    public static boolean isEmptyAccept(String login, String password) {
        return login != null && !login.trim().isEmpty() &&
                password != null && !password.trim().isEmpty();
    }

    public static int getAccept(String login, String password) {
        String sql = "SELECT role_id FROM users WHERE login = ? AND password = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("role_id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка авторизации: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean validateUserData(String login, String password, String firstName, String lastName) {
        if (login == null || login.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Логин не может быть пустым.");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Пароль не может быть пустым.");
            return false;
        }
        if (login.contains(" ") || password.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Логин и пароль не должны содержать пробелов.");
            return false;
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Имя не может быть пустым.");
            return false;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Фамилия не может быть пустой.");
            return false;
        }
        return true;
    }
}