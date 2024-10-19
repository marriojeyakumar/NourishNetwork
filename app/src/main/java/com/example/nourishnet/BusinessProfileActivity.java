package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BusinessProfileActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private String userEmail;

    // Declare all the UI elements for business profile data
    private TextView textViewBusinessName, textViewBusinessType, textViewBusinessAddress,
            textViewContactPerson, textViewContactEmail, textViewContactPhoneNumber,
            textViewSurplusTypes, textViewSurplusQuantity, textViewPickupTime, textViewPreferredMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        // Initialize views
        textViewBusinessName = findViewById(R.id.textViewBusinessName);
        textViewBusinessType = findViewById(R.id.textViewBusinessType);
        textViewBusinessAddress = findViewById(R.id.textViewBusinessAddress);
        textViewContactPerson = findViewById(R.id.textViewContactPerson);
        textViewContactEmail = findViewById(R.id.textViewContactEmail);
        textViewContactPhoneNumber = findViewById(R.id.textViewContactPhoneNumber);
        textViewSurplusTypes = findViewById(R.id.textViewSurplusTypes);
        textViewSurplusQuantity = findViewById(R.id.textViewSurplusQuantity);
        textViewPickupTime = findViewById(R.id.textViewPickupTime);
        textViewPreferredMethod = findViewById(R.id.textViewPreferredMethod);

        // Get email from previous intent
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Fetch business profile data
        fetchBusinessProfileData(userEmail);

        // Set up bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_connect) {
                    navigateToActivity(ConnectActivity.class);
                    return true;
                }
                return false;
            }
        });
    }

    // Method to fetch business profile data from Firestore
    private void fetchBusinessProfileData(String email) {
        db.collection("foodBusinesses").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Map fields from Firestore to the corresponding views
                    textViewBusinessName.setText("Business Name: " + document.getString("businessName"));
                    textViewBusinessType.setText("Business Type: " + document.getString("businessType"));
                    textViewBusinessAddress.setText("Business Address: " + document.getString("businessAddress"));
                    textViewContactPerson.setText("Contact Person: " + document.getString("contactPerson"));
                    textViewContactEmail.setText("Contact Email: " + document.getString("contactEmail"));
                    textViewContactPhoneNumber.setText("Contact Phone: " + document.getString("contactPhone"));
                    textViewSurplusTypes.setText("Surplus Types: " + document.getString("surplusTypes"));
                    textViewSurplusQuantity.setText("Surplus Quantity: " + document.getString("surplusQuantity"));
                    textViewPickupTime.setText("Pickup Time: " + document.getString("pickupTime"));
                    textViewPreferredMethod.setText("Preferred Method: " + document.getString("preferredMethod"));
                } else {
                    Toast.makeText(BusinessProfileActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(BusinessProfileActivity.this, "Fetch failed: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }
}
