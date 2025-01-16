package com.athabasca;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        System.out.println("App main method started");
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Creating Login window");
                Login login = new Login();
                System.out.println("Login window created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}