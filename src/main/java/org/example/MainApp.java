package org.example;

import org.example.view.FormAuth;

public class MainApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FormAuth().setVisible(true));
    }
}