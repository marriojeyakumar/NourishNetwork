package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BusinessDetailedSignupActivity extends AppCompatActivity {
    private static final String TAG = "BusinessSignupActivity";

    private EditText editTextBusinessName, editTextBusinessType, editTextBusinessAddress, editTextContactPerson,
            editTextContactEmail, editTextContactPhone, editTextSurplusTypes, editTextSurplusQuantity,
            editTextPickupTime, editTextPreferredMethod;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detailed_signup);

        // Initialize views
        editTextBusinessName = findViewById(R.id.editTextBusinessName);
        editTextBusinessType = findViewById(R.id.editTextBusinessType);
        editTextBusinessAddress = findViewById(R.id.editTextBusinessAddress);
        editTextContactPerson = findViewById(R.id.editTextContactPerson);
        editTextContactPhone = findViewById(R.id.editTextContactPhone);
        editTextSurplusTypes = findViewById(R.id.editTextSurplusTypes);
        editTextSurplusQuantity = findViewById(R.id.editTextSurplusQuantity);
        editTextPickupTime = findViewById(R.id.editTextPickupTime);
        editTextPreferredMethod = findViewById(R.id.editTextPreferredMethod);

        // Initialize Firebase Firestore and Auth
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // You may get these from a previous activity
        Intent prevIntent = getIntent();
        email = prevIntent.getStringExtra("email");
        password = prevIntent.getStringExtra("password");
        Log.d("JohnJohnJohnJohnJohn", "submitForm: Submitting business data.");
    }

    // Method to submit form
    public void submitForm(View view) {
        Log.d(TAG, "submitForm: Submitting business data.");

        String businessName = editTextBusinessName.getText().toString();
        String businessType = editTextBusinessType.getText().toString();
        String businessAddress = editTextBusinessAddress.getText().toString();
        String contactPerson = editTextContactPerson.getText().toString();
        String contactEmail = editTextContactEmail.getText().toString();
        String contactPhone = editTextContactPhone.getText().toString();
        String surplusTypes = editTextSurplusTypes.getText().toString();
        String surplusQuantity = editTextSurplusQuantity.getText().toString();
        String pickupTime = editTextPickupTime.getText().toString();
        String preferredMethod = editTextPreferredMethod.getText().toString();

        // Validate form fields
        if (businessName.isEmpty() || businessType.isEmpty() || businessAddress.isEmpty() || contactPerson.isEmpty() ||
                contactEmail.isEmpty() || contactPhone.isEmpty() || surplusTypes.isEmpty() || surplusQuantity.isEmpty() ||
                pickupTime.isEmpty() || preferredMethod.isEmpty()) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map to store business data
        Map<String, Object> businessData = new HashMap<>();
        businessData.put("businessName", businessName);
        businessData.put("businessType", businessType);
        businessData.put("businessAddress", businessAddress);
        businessData.put("contactPerson", contactPerson);
        businessData.put("contactEmail", contactEmail);
        businessData.put("contactPhone", contactPhone);
        businessData.put("surplusTypes", surplusTypes);
        businessData.put("surplusQuantity", surplusQuantity);
        businessData.put("pickupTime", pickupTime);
        businessData.put("preferredMethod", preferredMethod);

        // Store data in Firestore
        db.collection("foodBusinesses").document(contactEmail).set(businessData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(BusinessDetailedSignupActivity.this, "Business data saved successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BusinessDetailedSignupActivity.this, "Failed to save business data.", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Error saving business data.", task.getException());
                        }
                    }
                });

        // Optionally register user with Firebase Auth
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User registered successfully.");
                        } else {
                            Log.e(TAG, "User registration failed.", task.getException());
                        }
                    }
                });
    }
}
