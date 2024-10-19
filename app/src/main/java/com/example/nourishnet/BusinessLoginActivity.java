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
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        buttonLogIn = findViewById(R.id.signUpButton);
        buttonSignUp = findViewById(R.id.logInButton);
    }

    // Method for logging in
    public void login(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        Intent intent = new Intent(this, BusinessProfileActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);


    }

    // Method for forgot password

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
