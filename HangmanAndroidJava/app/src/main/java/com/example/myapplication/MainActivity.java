package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static String selectedDifficulty = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finds button
        Button play = findViewById(R.id.button);
        Button diff = findViewById(R.id.button3);
        Button custom = findViewById(R.id.button2);
        ImageView set = findViewById(R.id.imageView2);



        //sets button to on click
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startPlaying(selectedDifficulty);
            }
        });

        // START DIFFICULTY SECTION



        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DifficultyScreen activity
                startActivity(new Intent(MainActivity.this, difficulty_screen.class));

                // After user picks difficulty
                Intent intent = getIntent();
                String selectedDiff = intent.getStringExtra("difficulty");
                changeDifficulty(selectedDiff);

            }

        });

        // END DIFFICULTY SECTION


        custom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startActivity(new Intent(MainActivity.this, customize_screen.class));
            }
        });
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startActivity(new Intent(MainActivity.this, settings_screen.class));
            }
        });


    }

    // Below = ATTEMPT to receive user input of difficulty from diff_screen to MainActivity to hang_game
    // Method to handle difficulty change
    public void changeDifficulty(String difficulty) {
        selectedDifficulty = difficulty;
        //Toast.makeText(MainActivity.this, "Difficulty : " + selectedDifficulty, Toast.LENGTH_SHORT).show();
    }
    public String getDifficulty() {
        return selectedDifficulty;
    }
    // ATTEMPT to receive user input of difficulty from diff_screen to MainActivity to hang_game
    // Method to start playing with selected difficulty
    private void startPlaying(String difficulty) {
        Intent intent = new Intent(MainActivity.this, hang_game.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Difficulty : " + getDifficulty(), Toast.LENGTH_SHORT).show();
    }


}
