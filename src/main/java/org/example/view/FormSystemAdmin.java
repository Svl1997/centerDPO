package org.example.view;

import org.example.database.ConnectionDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FormSystemAdmin extends JFrame {
    private DefaultTableModel userTableModel;
    private JTable userTable;

    public FormSystemAdmin() {
        setTitle("Администратор системы - Управление пользователями");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        loadUsers();
    }

    private void initComponents() {
        // Таблица пользователей
        userTableModel = new DefaultTableModel(new String[]{"ID", "Логин", "Роль", "Фамилия", "Имя", "Отчество", "Дата регистрации"}, 0);
        userTable = new JTable(userTableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(userTable);

        // Панель кнопок управления пользователями
        JPanel userButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAddUser = new JButton("Добавить пользователя");
        JButton btnEditUser = new JButton("Редактировать пользователя");
        JButton btnDeleteUser = new JButton("Удалить пользователя");
        JButton btnRefreshUsers = new JButton("Обновить список");
        userButtonPanel.add(btnAddUser);
        userButtonPanel.add(btnEditUser);
        userButtonPanel.add(btnDeleteUser);
        userButtonPanel.add(btnRefreshUsers);

        // Панель кнопок для перехода к другим формам
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnStudents = new JButton("Студенты");
        JButton btnTeachers = new JButton("Преподаватели");
        JButton btnPrograms = new JButton("Программы");
        JButton btnCourses = new JButton("Курсы");
        JButton btnExit = new JButton("Выход");
        navPanel.add(btnStudents);
        navPanel.add(btnTeachers);
        navPanel.add(btnPrograms);
        navPanel.add(btnCourses);
        navPanel.add(btnExit);

        // Размещение
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(userButtonPanel, BorderLayout.NORTH);
        add(navPanel, BorderLayout.SOUTH);

        // Обработчики для пользователей
        btnAddUser.addActionListener(e -> new FormUserAdd(this).setVisible(true));
        btnEditUser.addActionListener(e -> editUser());
        btnDeleteUser.addActionListener(e -> deleteUser());
        btnRefreshUsers.addActionListener(e -> loadUsers());

        // Обработчики перехода к другим формам
        btnStudents.addActionListener(e -> {
            new FormAddStudent(this).setVisible(true);
            this.setVisible(false);
        });
        btnTeachers.addActionListener(e -> {
            new FormAddTeacher(this).setVisible(true);
            this.setVisible(false);
        });
        btnPrograms.addActionListener(e -> {
            new FormAddProgram(this).setVisible(true);
            this.setVisible(false);
        });
        btnCourses.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Форма управления курсами в разработке");
            // здесь можно создать FormAddCourse
        });
        btnExit.addActionListener(e -> {
            new FormAuth().setVisible(true);
            dispose();
        });
    }

    private void loadUsers() {
        userTableModel.setRowCount(0);
        String sql = "SELECT u.id, u.login, r.name as role_name, u.last_name, u.first_name, u.middle_name, u.registration_date " +
                "FROM users u JOIN roles r ON u.role_id = r.id ORDER BY u.id";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                userTableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("role_name"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("registration_date")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки пользователей: " + e.getMessage());
        }
    }

    private void editUser() {
        int row = userTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пользователя для редактирования.");
            return;
        }
        int userId = (int) userTableModel.getValueAt(row, 0);
        String login = (String) userTableModel.getValueAt(row, 1);
        // Открываем окно редактирования (можно сделать отдельную форму)
        new FormUserEdit(this, userId).setVisible(true);
        loadUsers(); // обновляем после редактирования
    }

    private void deleteUser() {
        int row = userTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пользователя для удаления.");
            return;
        }
        int userId = (int) userTableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Удалить пользователя?", "Подтверждение", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = ConnectionDB.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
                pstmt.setInt(1, userId);
                pstmt.executeUpdate();
                loadUsers();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Ошибка удаления: " + e.getMessage());
            }
        }
    }

    public void refreshUsers() {
        loadUsers();
    }
}