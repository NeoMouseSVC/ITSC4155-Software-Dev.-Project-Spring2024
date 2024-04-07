package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class difficulty_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_difficulty_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /* Return button DEPRECATED
        Button returnButton = findViewById(R.id.return_difficulty);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(difficulty_screen.this, MainActivity.class));
            }
        });
        */

        // Initialize buttons
        Button btnBeginner = findViewById(R.id.btnBeginner);
        Button btnIntermediate = findViewById(R.id.btnIntermediate);
        Button btnAdvanced = findViewById(R.id.btnAdvanced);

        // Set click listeners for buttons
        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass difficulty back to MainActivity
                sendDifficultyBack("Easy");
            }
        });

        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass difficulty back to MainActivity
                sendDifficultyBack("Medium");
            }
        });

        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass difficulty back to MainActivity
                sendDifficultyBack("Hard");
            }
        });
    }

    private void sendDifficultyBack(String difficulty) {
        // Create an intent to pass data back to MainActivity
        //Toast.makeText(difficulty_screen.this, "Difficulty: " + difficulty, Toast.LENGTH_SHORT).show();
        Intent data = new Intent();
        data.putExtra("difficulty", difficulty);
        setResult(RESULT_OK, data);
        finish(); // Finish this activity to return to MainActivity
    }

}