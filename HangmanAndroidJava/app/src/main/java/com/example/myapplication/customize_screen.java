package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class customize_screen extends AppCompatActivity {

    TextView currencyView;

    private ThemeFragment themeFragment;
    private StoreFragment storeFragment;
    private CharacterFragment characterFragment;

    public static int themeButton1 = 0;
    public static int themeButton2;
    public static int themeButton3;
    public static int themeButton4;
    public static int themeButton5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_screen);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Retrieve the saved background drawable ID from shared preferences
        int backgroundDrawableId = PreferenceUtils.getThemeButton1(this);
        updateBackground(backgroundDrawableId);


        currencyView = findViewById(R.id.textView3);
        currencyView.setText(MainActivity.currencyText);

        themeFragment = new ThemeFragment();
        storeFragment = new StoreFragment();
        characterFragment = new CharacterFragment();


            Button btnCharacter = findViewById(R.id.btnCharacter);
            btnCharacter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, CharacterFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name") // Name can be null
                            .commit();
                }
            });

        Button btnTheme = findViewById(R.id.btnTheme);
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ThemeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();
            }
        });

        Button btnStore = findViewById(R.id.btnStore);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, StoreFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();
            }
        });


        Button returnButton = findViewById(R.id.return_customize);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customize_screen.this, MainActivity.class));
            }
        });
        };
        public void updateBackground(int drawableId) {
            ConstraintLayout mainLayout = findViewById(R.id.main);
            mainLayout.setBackgroundResource(drawableId);

            // Save the selected background drawable ID in shared preferences
            PreferenceUtils.saveThemeButton1(this, drawableId);
        }

        public ThemeFragment getThemeFragment() {
            return themeFragment;
        }
        public StoreFragment getStoreFragment() {
            return storeFragment;
        }
        public CharacterFragment getCharacterFragment() {
            return characterFragment;
        }

    }
