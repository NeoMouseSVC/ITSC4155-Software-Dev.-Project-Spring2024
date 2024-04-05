package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String selectedDifficulty = "";
    public String getSelectedDiff() {
        return selectedDifficulty;
    }
    public void setSelectedDiff(String setter) {
        selectedDifficulty = setter;
    }
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>(){
                        @Override
                        public void onActivityResult(ActivityResult activityResult){
                            int result = activityResult.getResultCode();
                            Intent data = activityResult.getData();

                            if(result == RESULT_OK) {
                                if(data != null){
                                    String selectedDiff = data.getStringExtra("difficulty");
                                    setSelectedDiff(selectedDiff);
                                    Toast.makeText(MainActivity.this, "Difficulty : " + selectedDiff, Toast.LENGTH_SHORT).show();
                                    //Log.i("Difficulty Toast",selectedDiff);

                                }else {
                                    Toast.makeText(MainActivity.this, "Difficulty is null ", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Difficulty not selected ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    private void startPlaying(String difficulty) {
        Intent intent = new Intent(MainActivity.this, hang_game.class);
        intent.putExtra("difficulty", difficulty);
        //Log.i("Difficulty Toast",difficulty);
        startActivity(intent);
    }

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
                startPlaying(getSelectedDiff());
            }
        });

        // START DIFFICULTY SECTION

        diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DifficultyScreen activity
                Intent intent = new Intent(MainActivity.this, difficulty_screen.class);
                activityResultLauncher.launch(intent);
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


}
