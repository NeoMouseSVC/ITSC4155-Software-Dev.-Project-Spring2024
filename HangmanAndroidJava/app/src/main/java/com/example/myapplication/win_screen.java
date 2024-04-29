package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class win_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_win_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button continueButton = findViewById(R.id.los_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(win_screen.this, hang_game.class);

                // Retrieve the difficulty and category passed to lose_screen
                String difficulty = getIntent().getStringExtra("difficulty");
                String category = getIntent().getStringExtra("category");

                // Add them as extras to the intent for starting a new hang_game
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("category", category);

                startActivity(intent);
                finish(); // Ensure the current activity is finished to avoid multiple instances
            }
        });
        Button ExitButton = findViewById(R.id.los_exit);
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startActivity(new Intent(win_screen.this, MainActivity.class));
            }
        });
    }


}