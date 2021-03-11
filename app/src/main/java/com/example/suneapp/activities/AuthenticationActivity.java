package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;
import com.example.suneapp.services.FirebaseService;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "Authentication";
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        // Initialize Firebase Auth on create activity
        firebaseService = new FirebaseService();
    }

    public void authenticateUser(View view) {
        EditText email = findViewById(R.id.loginInput);
        EditText password = findViewById(R.id.passwordInput);

        String emailAnswer = email.getText().toString();
        String passwordAnswer = password.getText().toString();

        if (emailAnswer.isEmpty() || passwordAnswer.isEmpty())
            Toast.makeText(getApplicationContext(),
                    "Please enter an email and password !", Toast.LENGTH_SHORT).show();
//
//        if (!emailAnswer.matches("^(.+)@(.+)$") ||
//                !passwordAnswer
//                        .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{6,20}$"))
//            Toast.makeText(getApplicationContext(),
//                    "Please enter a valid email and password !", Toast.LENGTH_SHORT).show();
//        else {
            // call Firebase authentication service
            boolean isLogged = signIn(emailAnswer, passwordAnswer);
            if(isLogged)
                // redirect to LogsActivity
                startActivity(new Intent(this, LogsActivity.class));
//        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseService.getUser();
        if(currentUser != null){
            //TODO make an action
        }
    }

    public boolean signIn(String email, String password) {

        firebaseService.signIn(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }
                });
        return firebaseService.getUser() != null;
    }
}