package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormAddProgram extends JFrame {
    private JFrame parentFrame;

    private JTextField txtProgramId, txtName, txtDescription, txtDurationHours, txtCategory;
    private JButton btnSave, btnCancel;

    public FormAddProgram(JFrame parent) {
        this.parentFrame = parent;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Добавление программы обучения");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnSave.addActionListener(e -> saveProgram());
        btnCancel.addActionListener(e -> cancel());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (parentFrame != null) parentFrame.setVisible(true);
            }
        });
    }

    private void initComponents() {
        // Поля ввода
        txtProgramId = new JTextField(15);
        txtName = new JTextField(20);
        txtDescription = new JTextField(30);
        txtDurationHours = new JTextField(10);
        txtCategory = new JTextField(15);

        // Подписи
        JLabel lblProgramId = new JLabel("Program ID:");
        JLabel lblName = new JLabel("Наименование:");
        JLabel lblDescription = new JLabel("Описание:");
        JLabel lblDurationHours = new JLabel("Продолжительность (часов):");
        JLabel lblCategory = new JLabel("Категория:");

        // Кнопки
        btnSave = new JButton("Сохранить");
        btnCancel = new JButton("Отмена");

        // Размещение с помощью GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        addPair(lblProgramId, txtProgramId, row++, gbc);
        addPair(lblName, txtName, row++, gbc);
        addPair(lblDescription, txtDescription, row++, gbc);
        addPair(lblDurationHours, txtDurationHours, row++, gbc);
        addPair(lblCategory, txtCategory, row++, gbc);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = row;
        add(buttonPanel, gbc);

        pack();
    }

    private void addPair(JLabel label, JComponent field, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(label, gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    private void saveProgram() {
        String programId = txtProgramId.getText().trim();
        String name = txtName.getText().trim();
        String duration = txtDurationHours.getText().trim();

        if (programId.isEmpty() || name.isEmpty() || duration.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заполните обязательные поля: Program ID, Наименование, Продолжительность (часов)");
            return;
        }


        JOptionPane.showMessageDialog(this, "Программа обучения будет добавлена \n" + name + " (" + programId + ")");

        if (parentFrame != null) parentFrame.setVisible(true);
        dispose();
    }

    private void cancel() {
        if (parentFrame != null) parentFrame.setVisible(true);
        dispose();
    }
}