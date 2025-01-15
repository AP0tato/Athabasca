package com.athabasca;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class DatabaseUtil
{
    public static final String URL = "https://finalproject12-1fd07-default-rtdb.firebaseio.com";
    private FirebaseOptions options;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Object data;

    public DatabaseUtil()
    {
        try 
        {
            // Load the service account key JSON file
            FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/serviceAccountKey.json");

            // Configure Firebase options
            options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DatabaseUtil.URL)
                .build();

            // Initialize the Firebase app if not already initialized
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Get instances of FirebaseDatabase
            database = FirebaseDatabase.getInstance();
        } 
        catch (IOException e) 
        {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setRef(String pathToData, Consumer<Object> callback) { 
        System.out.println("Setting reference to path: " + pathToData);
        ref = database.getReference(pathToData); 
        System.out.println("Reference set to path: " + pathToData);

        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() 
            {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    fknWokr(snapshot.getValue());
                    System.out.println("Data retrieved: " + data);
                    callback.accept(data); // Invoke the callback with the retrieved data
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.err.println("Database error: " + error.getMessage());
                }
            });
        } else {
            System.err.println("Reference is null for path: " + pathToData);
        }
    }

    private void fknWokr(Object e) {
        this.data = e;
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

    public Object getData() {
        return data;
    }
}
