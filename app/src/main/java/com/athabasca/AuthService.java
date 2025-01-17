package com.athabasca;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AuthService {
    private FirebaseAuth auth;
    private static final String FIREBASE_API_KEY = "AIzaSyAxATCcxeG0shBCxPyo_ZdEhDhb4W0qaRU";

    public AuthService() {
        new DatabaseUtil();
        auth = FirebaseAuth.getInstance();
    }

    public String loginUserAndToken(String email, String password) {
        try {
            String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + FIREBASE_API_KEY;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(con.getInputStream())) {
                    String responseBody = scanner.useDelimiter("\\A").next();
                    JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
                    String idToken = jsonObject.get("idToken").getAsString();
                    System.out.println("Successfully logged in and obtained ID token: " + idToken);
                    return idToken;
                }
            } else {
                try (Scanner scanner = new Scanner(con.getErrorStream())) {
                    String errorResponse = scanner.useDelimiter("\\A").next();
                    System.err.println("Error response: " + errorResponse);
                }
            }
        } catch (Exception e) {
            System.err.println("Error logging in user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void verifyIdToken(String idToken) {
        try {
            FirebaseToken decodedToken = auth.verifyIdToken(idToken);
            System.out.println("Successfully verified ID token: " + decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            System.err.println("Error verifying ID token: " + e.getMessage());
            e.printStackTrace();
        }
    }
}