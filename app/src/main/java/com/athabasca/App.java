package com.athabasca;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            // Load the service account key JSON file
            FileInputStream serviceAccount = new FileInputStream("/serviceAccountKey.json");

            // Configure Firebase options
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://finalproject12-1fd07-default-rtdb.firebaseio.com")
                .build();

            // Initialize Firebase App
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully!");
            } else {
                System.out.println("Firebase App already initialized.");
            }
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getGreeting() { return "Hi"; }
}
