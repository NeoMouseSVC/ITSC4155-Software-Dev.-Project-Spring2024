package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class category_screen extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        /* Initialize ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Handle result if needed
                    }
                });
        */
        // Set click listeners for category buttons
        setButtonListeners();
    }

    private void setButtonListeners() {
        Button btnCategory1 = findViewById(R.id.btnCategory1);
        Button btnCategory2 = findViewById(R.id.btnCategory2);
        Button btnCategory3 = findViewById(R.id.btnCategory3);
        Button btnCategory4 = findViewById(R.id.btnCategory4);
        Button btnCategory5 = findViewById(R.id.btnCategory5);


        btnCategory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBack("All");
            }
        });

        btnCategory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBack("MTV");
            }
        });

        btnCategory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBack("Foods");
            }
        });

        btnCategory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBack("Countries");
            }
        });

        btnCategory5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBack("Video Games");
            }
        });

    }

    private void sendCategoryBack(String category) {
        // Create intent to pass data back to ModifierScreenActivity
        Intent data = new Intent();
        data.putExtra("category", category);
        setResult(RESULT_OK, data);
        finish();
    }
}

