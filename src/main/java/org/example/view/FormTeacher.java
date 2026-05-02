package org.example.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FormTeacher extends JFrame {
    private JButton btnLogout;
    private JLabel lblWelcome;

    public FormTeacher() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Панель преподавателя");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        lblWelcome = new JLabel("Добро пожаловать в панель преподавателя!", SwingConstants.CENTER);
        btnLogout = new JButton("Выйти");

        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblWelcome)
                        .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(50)
                        .addComponent(lblWelcome)
                        .addGap(30)
                        .addComponent(btnLogout)
                        .addGap(50)
        );

        pack();
    }

    private void btnLogoutActionPerformed(ActionEvent evt) {
        new FormAuth().setVisible(true);
        dispose();
    }
}