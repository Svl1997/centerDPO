package org.example.view;

import org.example.controller.Controller;
import javax.swing.JOptionPane;


public class FormAuth extends javax.swing.JFrame {
public FormAuth() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Авторизация");
        txtLogin.setText("");
        txtPassword.setText("");
        btnCancel.addActionListener(this::btnCancelActionPerformed);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Авторизация");

        btnLogin.setText("Вход");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnCancel.setText("Отмена");

        txtPassword.setText("jPasswordField1");
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        jLabel1.setText("Логин");

        jLabel2.setText("Пароль");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnCancel))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String login = txtLogin.getText();
        String password = new String(txtPassword.getPassword());

        if (Controller.isEmptyAccept(login, password)) {
            int role = Controller.getAccept(login, password);
            if (role > 0) {
                String welcomeMsg;
                switch (role) {
                    case 1:
                        welcomeMsg = "Добро пожаловать, Администратор!";
                        break;
                    case 2:
                        welcomeMsg = "Добро пожаловать, Преподаватель!";
                        break;
                    case 3:
                        welcomeMsg = "Добро пожаловать, Гость!";
                        break;
                    default:
                        welcomeMsg = "Добро пожаловать!";
                }
                JOptionPane.showMessageDialog(this, welcomeMsg);


                if (role == 1) {
                    new FormSystemAdmin().setVisible(true);
                    dispose();
                } else if (role == 2) {

                    new FormTeacher().setVisible(true);
                    dispose();
                } else if (role == 3) {
                    new FormGuest().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Неизвестная роль");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Неверный логин или пароль");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Заполните логин и пароль");
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
