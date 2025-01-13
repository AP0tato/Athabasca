package com.athabasca;

import java.io.FileInputStream;

import javax.swing.JFrame;

public class App extends JFrame
{
    public String getGreeting() { return "Hi"; }

    public App()
    {
        FileInputStream serviceAccount =
            new FileInputStream("/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://finalproject12-1fd07-default-rtdb.firebaseio.com")
            .build();

        FirebaseApp.initializeApp(options);
    }
}
