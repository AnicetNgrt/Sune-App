package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;
import com.example.suneapp.services.FirebaseService;

import java.util.regex.Pattern;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "Authentication";
    private FirebaseService firebaseService;
    private boolean isAuthenticated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        // Initialize Firebase Auth on create activity
        firebaseService = new FirebaseService();
        isAuthenticated = false;
    }

    public void authenticateUser(View view) {
        EditText email = findViewById(R.id.loginInput);
        EditText password = findViewById(R.id.passwordInput);

        String emailAnswer = email.getText().toString();
        String passwordAnswer = password.getText().toString();

        if (emailAnswer.isEmpty() || passwordAnswer.isEmpty())
            Toast.makeText(getApplicationContext(),
                    "Please enter an email and password !", Toast.LENGTH_SHORT).show();

        boolean emailMatch = Patterns.EMAIL_ADDRESS.matcher(emailAnswer).matches();
        Pattern pwdPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\\\\\S+$).{6,20}$");

        if(!(emailMatch || pwdPattern.matcher(passwordAnswer).matches())){
            Log.e(TAG, "Wrong credentials");
            Toast.makeText(getApplicationContext(),
        "Please enter a valid email and password !", Toast.LENGTH_SHORT).show();
        }
        else {
//             call Firebase authentication service, we comment that for dev purpose
            signIn(emailAnswer, passwordAnswer);
            if(isAuthenticated)
//                 redirect to LogsActivity
                startActivity(new Intent(this, LogsActivity.class));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = firebaseService.getUser();
//        if(currentUser != null)
//            startActivity(new Intent(this, LogsActivity.class));
    }

    private void signIn(String email, String password) {
        firebaseService.signIn(email, password)
                .addOnSuccessListener(this, task -> {
                    Log.d(TAG, "signInWithEmail:success");
                    setAuthenticated(true);
                })
                .addOnFailureListener(this, task -> {
                    setAuthenticated(false);
                    Log.e(TAG, "signInWithEmail:failure", task.getCause());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                });
    }

    private void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}