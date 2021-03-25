package com.example.suneapp.unittest;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLoginResolver {
    private boolean isAuthenticated;

    public FirebaseLoginResolver() {
        this.isAuthenticated = false;
    }

    public void signIn(String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(task -> {
                    setAuthenticated(true);
                });
    }

    private void setAuthenticated(boolean authenticated) {
        this.isAuthenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
