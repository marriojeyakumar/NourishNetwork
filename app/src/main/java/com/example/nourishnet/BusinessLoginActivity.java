package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BusinessLoginActivity extends AppCompatActivity {

    private static final String TAG = "BusinessLoginActivity";
    private EditText emailEditText, passwordEditText;
    private Button buttonLogIn, buttonSignUp;
    private String email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        buttonLogIn = findViewById(R.id.signUpButton);
        buttonSignUp = findViewById(R.id.loginButton);
    }

    // Method for logging in
    public void login(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        // Validate fields
        if (email.isEmpty() || password.isEmpty()) {
            alert("Please fill out both fields.");
            return;
        }

        // Authenticate user with Firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success, update UI with user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithEmail:success");
                            updateUI(user);
                        } else {
                            // If sign in fails, show error
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            alert("Authentication failed.");
                            updateUI(null);
                        }
                    }
                });
    }

    // Method for forgot password
    public void forgotPassword(View view) {
        email = emailEditText.getText().toString();

        if (email.isEmpty()) {
            alert("Please enter your email.");
            return;
        }

        // Send password reset email
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "sendPasswordResetEmail:success");
                            alert("Password reset email sent.");
                        } else {
                            Log.w(TAG, "sendPasswordResetEmail:failure", task.getException());
                            alert("Error sending password reset email.");
                        }
                    }
                });
    }

    // Method to go to the signup page
    public void goToSignup(View view) {
        Intent intent = new Intent(this, BusinessSignupActivity.class);
        startActivity(intent);
    }

    // Method to update UI based on user's authentication status
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.d(TAG, "updateUI: user is not null, proceeding to business profile");
            Intent intent = new Intent(this, BusinessProfileActivity.class);
            intent.putExtra("email", user.getEmail());
            startActivity(intent);
            finish();
        } else {
            Log.d(TAG, "updateUI: user is null");
        }
    }

    // Helper method to show a Toast message
    public void alert(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
