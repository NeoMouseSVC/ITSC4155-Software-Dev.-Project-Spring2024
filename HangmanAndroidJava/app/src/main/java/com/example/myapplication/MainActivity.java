package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
                startActivity(new Intent(MainActivity.this, hang_game.class));
            }
        });
        diff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ play button
                startActivity(new Intent(MainActivity.this, diffculty_screen.class));
            }
        });
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
