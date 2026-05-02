package org.example.view;

import org.example.database.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FormUserEdit extends JDialog {
    private int userId;
    private JTextField tfLogin, tfLastName, tfFirstName, tfMiddleName;
    private JPasswordField pfPassword;
    private JComboBox<String> cbRole;

    public FormUserEdit(JFrame parent, int userId) {
        super(parent, "Редактирование пользователя", true);
        this.userId = userId;
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
        loadUserData();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        tfLogin = new JTextField(15);
        pfPassword = new JPasswordField(15);
        tfLastName = new JTextField(15);
        tfFirstName = new JTextField(15);
        tfMiddleName = new JTextField(15);
        cbRole = new JComboBox<>();
        loadRoles();

        JButton btnSave = new JButton("Сохранить");
        JButton btnCancel = new JButton("Отмена");

        int row = 0;
        addRow("Логин:", tfLogin, row++, gbc);
        addRow("Пароль (оставьте пустым, если не менять):", pfPassword, row++, gbc);
        addRow("Роль:", cbRole, row++, gbc);
        addRow("Фамилия:", tfLastName, row++, gbc);
        addRow("Имя:", tfFirstName, row++, gbc);
        addRow("Отчество:", tfMiddleName, row++, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
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

    private void loadUserData() {
        String sql = "SELECT login, role_id, last_name, first_name, middle_name FROM users WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tfLogin.setText(rs.getString("login"));
                tfLastName.setText(rs.getString("last_name"));
                tfFirstName.setText(rs.getString("first_name"));
                tfMiddleName.setText(rs.getString("middle_name"));
                // выбираем роль в комбобоксе
                int roleId = rs.getInt("role_id");
                for (int i = 0; i < cbRole.getItemCount(); i++) {
                    if (cbRole.getClientProperty(cbRole.getItemAt(i)).equals(roleId)) {
                        cbRole.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
        }
    }

    private void saveUser() {
        String login = tfLogin.getText().trim();
        String password = new String(pfPassword.getPassword()).trim();
        String roleName = (String) cbRole.getSelectedItem();
        String lastName = tfLastName.getText().trim();
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();

        int roleId = (int) cbRole.getClientProperty(roleName);
        StringBuilder sql = new StringBuilder("UPDATE users SET login = ?, role_id = ?, last_name = ?, first_name = ?, middle_name = ?");
        if (!password.isEmpty()) sql.append(", password = ?");
        sql.append(" WHERE id = ?");

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            pstmt.setString(idx++, login);
            pstmt.setInt(idx++, roleId);
            pstmt.setString(idx++, lastName);
            pstmt.setString(idx++, firstName);
            pstmt.setString(idx++, middleName);
            if (!password.isEmpty()) pstmt.setString(idx++, password);
            pstmt.setInt(idx, userId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Пользователь обновлён");
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
        }
    }
}