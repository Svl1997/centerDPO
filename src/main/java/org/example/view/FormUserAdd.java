package org.example.view;

import org.example.controller.Controller;
import org.example.database.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FormUserAdd extends JDialog {
    private JFrame parent;
    private JTextField tfLogin, tfLastName, tfFirstName, tfMiddleName;
    private JPasswordField pfPassword;
    private JComboBox<String> cbRole;
    private JButton btnSave, btnCancel;

    public FormUserAdd(JFrame parent) {
        super(parent, "Добавление пользователя", true);
        this.parent = parent;
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Поля
        tfLogin = new JTextField(15);
        pfPassword = new JPasswordField(15);
        tfLastName = new JTextField(15);
        tfFirstName = new JTextField(15);
        tfMiddleName = new JTextField(15);

        // Роли – загружаем из БД
        cbRole = new JComboBox<>();
        loadRoles();

        // Кнопки
        btnSave = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");

        // Добавление на форму
        int row = 0;
        addRow("Логин:", tfLogin, row++, gbc);
        addRow("Пароль:", pfPassword, row++, gbc);
        addRow("Роль:", cbRole, row++, gbc);
        addRow("Фамилия:", tfLastName, row++, gbc);
        addRow("Имя:", tfFirstName, row++, gbc);
        addRow("Отчество:", tfMiddleName, row++, gbc);

        // Панель кнопок
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = row;
        add(buttonPanel, gbc);

        btnSave.addActionListener(e -> saveUser());
        btnCancel.addActionListener(e -> dispose());
    }

    private void addRow(String labelText, JComponent field, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    private void loadRoles() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM roles ORDER BY id")) {
            while (rs.next()) {
                cbRole.addItem(rs.getString("name"));
                cbRole.putClientProperty(rs.getString("name"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки ролей: " + e.getMessage());
        }
    }

    private void saveUser() {
        String login = tfLogin.getText().trim();
        String password = new String(pfPassword.getPassword()).trim();
        String roleName = (String) cbRole.getSelectedItem();
        String lastName = tfLastName.getText().trim();
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();

        // Валидация через Controller
        if (!Controller.validateUserData(login, password, firstName, lastName)) {
            return; // сообщение об ошибке уже показано в Controller
        }

        // Получаем role_id из выбранного пункта
        int roleId = 0;
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM roles WHERE name = ?")) {
            pstmt.setString(1, roleName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) roleId = rs.getInt("id");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка определения роли: " + e.getMessage());
            return;
        }

        // Вставка нового пользователя
        String sql = "INSERT INTO users (login, password, role_id, last_name, first_name, middle_name, registration_date) VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setInt(3, roleId);
            pstmt.setString(4, lastName);
            pstmt.setString(5, firstName);
            pstmt.setString(6, middleName);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Пользователь добавлен");
            dispose();
            if (parent instanceof FormSystemAdmin) {
                ((FormSystemAdmin) parent).refreshUsers(); // нужно добавить метод refreshUsers в FormSystemAdmin
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения: " + e.getMessage());
        }
    }
}