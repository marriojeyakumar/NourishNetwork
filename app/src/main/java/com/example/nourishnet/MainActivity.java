package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void businessLogin(View view) {
        Intent intent = new Intent(this, BusinessLoginActivity.class);
        startActivity(intent);
    }

    public void volunteerLogin(View view) {
        Intent intent = new Intent(this, VolunteerLoginActivity.class);
        startActivity(intent);
    }
}