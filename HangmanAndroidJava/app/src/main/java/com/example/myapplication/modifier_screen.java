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
import androidx.constraintlayout.widget.ConstraintLayout;

public class modifier_screen extends AppCompatActivity {


    private String selectedDifficulty = null;
    private String selectedCategory = null;
    ActivityResultLauncher<Intent> difficultyLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {
                            if (activityResult.getResultCode() == RESULT_OK) {
                                Intent data = activityResult.getData();
                                if(data != null){
                                    selectedDifficulty = data.getStringExtra("difficulty");
                                    //updateMainActivity();
                                }

                            }
                        }
                    });
    ActivityResultLauncher<Intent> categoryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {
                            if (activityResult.getResultCode() == RESULT_OK) {
                                Intent data = activityResult.getData();
                                if(data != null){
                                    selectedCategory = data.getStringExtra("category");
                                    //updateMainActivity();
                                }

                            }
                        }
                    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_screen);

        int selectedTheme = PreferenceUtils.getThemeButton1(this);
        findViewById(R.id.main).setBackgroundResource(selectedTheme);

        Button btnDifficulty = findViewById(R.id.btnDifficulty);
        Button btnCategory = findViewById(R.id.btnCategory);
        Button btnDone = findViewById(R.id.btnDone);

        btnDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch DifficultyScreenActivity
                Intent intent = new Intent(modifier_screen.this, difficulty_screen.class);
                difficultyLauncher.launch(intent);
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch CategoryScreenActivity
                Intent intent = new Intent(modifier_screen.this, category_screen.class);
                categoryLauncher.launch(intent);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMainActivity();
            }
        });

    }

    private void updateMainActivity() {
        // Send selected difficulty and category back to MainActivity
        Intent data = new Intent();
        data.putExtra("difficulty", selectedDifficulty);
        data.putExtra("category", selectedCategory);
        setResult(RESULT_OK, data);
        finish();
    }
}


