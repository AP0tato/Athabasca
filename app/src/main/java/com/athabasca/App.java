package com.athabasca;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class App extends JFrame
{
    private JLabel debugLbl;
    public App()
    {
        debugLbl = new JLabel();

        add(debugLbl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getGreeting() { return "Hi"; }
}
