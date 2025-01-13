package com.athabasca;

import java.io.FileInputStream;
import com.google.firebase.FirebaseApp; // For initializing the Firebase app
import com.google.firebase.FirebaseOptions; // For Firebase configuration options
import com.google.auth.oauth2.GoogleCredentials; // For authenticating using the service account

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
