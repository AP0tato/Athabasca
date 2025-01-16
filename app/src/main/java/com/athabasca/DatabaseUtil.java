package com.athabasca;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class DatabaseUtil {
    public static final String URL = "https://finalproject12-1fd07-default-rtdb.firebaseio.com";
    private FirebaseDatabase database;

    public DatabaseUtil() {
        try {
            // Load the service account key JSON file
            FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir") + "/app/src/main/resources/serviceAccountKey.json");

            // Configure Firebase options
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DatabaseUtil.URL)
                .build();

            // Initialize Firebase app
            FirebaseApp.initializeApp(options);

            // Get a reference to the database
            database = FirebaseDatabase.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRef(String path, Consumer<Object> callback) {
        DatabaseReference ref = database.getReference(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object data = snapshot.getValue();
                callback.accept(data);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error retrieving data: " + error.getMessage());
                callback.accept(null);
            }
        });
    }
}