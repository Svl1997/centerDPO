package org.example.view;

import javax.swing.JOptionPane;

    
public class FormAdmin extends javax.swing.JFrame {

    public FormAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        btnAddStudent.addActionListener(this::btnAddStudentActionPerformed);
        btnAddTeacher.addActionListener(this::btnAddTeacherActionPerformed);
        btnAddProgram.addActionListener(this::btnAddProgramActionPerformed);
        btnExit.addActionListener(this::btnExitActionPerformed);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddStudent = new javax.swing.JButton();
        btnAddTeacher = new javax.swing.JButton();
        btnAddProgram = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddStudent.setText("Добавить студента");

        btnAddTeacher.setText("Добавить преподавателя");

        btnAddProgram.setText("Добавить программу обучения");

        btnExit.setText("Выйти");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAddProgram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnAddStudent)
                .addGap(18, 18, 18)
                .addComponent(btnAddTeacher)
                .addGap(18, 18, 18)
                .addComponent(btnAddProgram)
                .addGap(39, 39, 39)
                .addComponent(btnExit)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {
        new FormAddStudent(this).setVisible(true);
        this.setVisible(false);
    }

    private void btnAddTeacherActionPerformed(java.awt.event.ActionEvent evt) {
        new FormAddTeacher(this).setVisible(true);
        this.setVisible(false);
    }

    private void btnAddProgramActionPerformed(java.awt.event.ActionEvent evt) {
        new FormAddProgram(this).setVisible(true);
        this.setVisible(false);
    }

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProgram;
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnAddTeacher;
    private javax.swing.JButton btnExit;
    // End of variables declaration//GEN-END:variables
}
