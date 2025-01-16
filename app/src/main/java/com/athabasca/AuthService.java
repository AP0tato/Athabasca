package com.athabasca;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

public class AuthService {
    private FirebaseAuth auth;

    public AuthService()
    {
        new DatabaseUtil();
        auth = FirebaseAuth.getInstance();
    }

    public String loginUserAndToken(String email, String password) {
        try {
            UserRecord record = auth.getUserByEmail(email);
            String token = auth.createCustomToken(record.getUid());
            System.out.println("Successfully created custom token: " + token);

            return token;
        } catch(FirebaseAuthException e) {
            System.err.println("Error creating user: " + e.getMessage());

        }
        return null;
    }

    public void verifyIdToken(String idToken) {
        try {
            FirebaseToken decodedToken = auth.verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            System.out.println("Successfully verified ID token. UID: " + uid);
        } catch (FirebaseAuthException e) {
            System.err.println("Error verifying ID token: " + e.getMessage());
        }
    }
}
