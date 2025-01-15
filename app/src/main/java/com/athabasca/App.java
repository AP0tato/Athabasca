package com.athabasca;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App 
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // TestFrame tf = new TestFrame();
            // Login login = new Login();
            ClientList cl = new ClientList();
        });
    }
}
