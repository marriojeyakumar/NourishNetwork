package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VolunteerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, VolunteerProfileActivity.class);
        startActivity(intent);
    }

    public void goToSignup(View view) {
        Intent intent = new Intent(this, VolunteerSignupActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {

    }
}