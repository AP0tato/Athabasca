package com.athabasca;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseUtil
{
    private FirebaseOptions options;
    public FirebaseDatabase database;
    private DatabaseReference ref;

    public DatabaseUtil()
    {
        try 
        {
            // Load the service account key JSON file
            FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/serviceAccountKey.json");

            // Configure Firebase options
            options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://finalproject12-1fd07-default-rtdb.firebaseio.com")
                .build();

            // Initialize the Firebase app if not already initialized
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Get instances of FirebaseDatabase and FirebaseAuth
            database = FirebaseDatabase.getInstance();
        } 
        catch (IOException e) 
        {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setRef(String pathToData) { 
        ref = database.getReference(pathToData); 

        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() 
            {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Object val = snapshot.getValue();
                    System.out.println("Data changed: " + val);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.err.println("Database error: " + error.getMessage());
                }
            });
        }
    }

    public String getRef() { 
        return ref != null ? ref.getKey() : null; 
    }

    public int write(String data)
    {
        if(getRef()!=null)
        {
            ref.setValue(data, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error != null)
                    {
                        System.err.println("Database error: " + error.getMessage());
                    }
                    else
                    {
                        System.out.println("Database operation completed successfully.");
                    }
                }
            });
        }
        else return 1;
        return 0;
    }
}
