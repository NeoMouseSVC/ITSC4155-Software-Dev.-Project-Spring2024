package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finds button
        Button play = findViewById(R.id.button);
        //sets button to on click
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // starts new activity/ aka new page 
                startActivity(new Intent(MainActivity.this, hang_game.class));
            }
        });

    }
}
