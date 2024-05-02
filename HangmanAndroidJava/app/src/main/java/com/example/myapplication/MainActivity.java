package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private String selectedDifficulty = "Easy";
    private String selectedCategory="All";

    public static int currencyCount = 0;

    TextView currencyView;
    public static String currencyText;


    public String getSelectedDiff() {
        return selectedDifficulty;
    }
    public void setSelectedDiff(String setter) {
        selectedDifficulty = setter;
    }
    private String getSelectedCategory() {
        return selectedCategory;
    }
    private void setSelectedCategory(String inputCategory) {
        selectedCategory = inputCategory;
    }
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {
                            int result = activityResult.getResultCode();
                            Intent data = activityResult.getData();

                            if (result == RESULT_OK && data != null) {
                                String selectedDiff = data.getStringExtra("difficulty");
                                String selectedCat = data.getStringExtra("category");

                                // Check if the user selected a difficulty
                                if (data.getStringExtra("difficulty") != null) {
                                    setSelectedDiff(selectedDiff);
                                    Log.i("Difficulty", "Difficulty set by user: " + selectedDiff);
                                } else {
                                    // If no difficulty selected, default to "Easy"
                                    setSelectedDiff("Easy");
                                    Log.i("Difficulty", "Difficulty not set - defaulted to Easy");
                                }

                                // Check if the user selected a category
                                if (data.getStringExtra("category") != null) {
                                    setSelectedCategory(selectedCat);
                                    Log.i("Category", "Category set by user: " + selectedCat);
                                } else {
                                    // If no category selected, default to "All"
                                    setSelectedCategory("All");
                                    Log.i("Category", "Category not set - defaulted to All");
                                }

                                Log.i("Selected Difficulty", getSelectedDiff());
                                Log.i("Selected Category", getSelectedCategory());
                            } else {
                                // Handle the case when no data is received or activity was canceled
                                // For example, show a toast or log a message
                            }
                        }
                    });

    private void startPlaying(String difficulty, String category) {
        Intent intent = new Intent(MainActivity.this, hang_game.class);
        intent.putExtra("difficulty", getSelectedDiff());
        intent.putExtra("category", getSelectedCategory());

        startActivity(intent);
    }



    private int getCurrencyCount() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("currency_count", 0); // Default value is 0 if not found
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int selectedTheme = PreferenceUtils.getThemeButton1(this);
        findViewById(R.id.main).setBackgroundResource(selectedTheme);

        //finds button
        Button play = findViewById(R.id.button);
        Button mod = findViewById(R.id.button3);
        Button custom = findViewById(R.id.button2);
        ImageView set = findViewById(R.id.imageView2);

        // Retrieve currency count from SharedPreferences
        currencyCount = PreferenceUtils.getCurrencyCount(this);
        PreferenceUtils.saveCurrencyCount(this, currencyCount);

        currencyView = currencyView = findViewById(R.id.textView3);
        currencyText =currencyText = String.valueOf(currencyCount);
        currencyView.setText(currencyText);




        //sets button to on click
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startPlaying(getSelectedDiff(), getSelectedCategory());
            }
        });

        // START MODIFIER SECTION (DIFFICULTY + CATEGORY)

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DifficultyScreen activity
                Intent intent = new Intent(MainActivity.this, modifier_screen.class);
                activityResultLauncher.launch(intent);
            }

        });

        // END MODIFIER SECTION


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
