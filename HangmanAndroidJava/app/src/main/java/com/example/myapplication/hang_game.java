package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class hang_game extends AppCompatActivity {

    private String[] words = {
            "apple", "banana", "cherry", "elderberry",
            "fig", "grape", "honeydew", "iceberg lettuce", "jalapeno",
            "kiwi", "lemon", "mango", "nectarine", "orange",
            "pineapple", "quinoa", "raspberry", "strawberry", "tomato",
            "ugli fruit", "vanilla bean", "watermelon", "yellow pepper",
            "zucchini", "yam", "wheat", "vinegar",
            "tea", "squash", "radish", "quail", "pumpkin"
    };

    private String currentWord;
    private char[] currentGuess;
    private int numberOfGuesses;
    private String selectedDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_game);

        Button hang_return = findViewById(R.id.hang_return);
        hang_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(hang_game.this, MainActivity.class));
            }
        });
        //Difficulty modifier
        Intent i = getIntent();
        if(i.getStringExtra("difficulty") != null){
            String selected = i.getStringExtra("difficulty");
            setSelectedDiff(i.getStringExtra("difficulty"));
            Log.i("Difficulty Toast",selected);
            TextView difficultyText = findViewById(R.id.difficulty);
            difficultyText.setText(getString(R.string.difficulty_text,selected));
            Toast.makeText(hang_game.this, "Difficulty : " + getSelectedDiff(), Toast.LENGTH_SHORT).show();

        }else{
            Log.i("Difficulty Failed","Difficulty Failed");
        }
        startGame();

    }

    private void startGame() {
        Random random = new Random();
        currentWord = words[random.nextInt(words.length)];
        currentGuess = new char[currentWord.length()];
        for (int i = 0; i < currentWord.length(); i++) {
            currentGuess[i] = '_';
        }
        numberOfGuesses = 6;

        updateScreen();

    }

    private void updateScreen() {
        TextView wordTextView = findViewById(R.id.madeit);
        wordTextView.setText(addSpaces(new String(currentGuess)));

        EditText guessField = findViewById(R.id.guessfeild);
        guessField.setText("");

        TextView chancesTextView = findViewById(R.id.chances_counter);
        chancesTextView.setText("Chances: " + numberOfGuesses);

        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = guessField.getText().toString();
                if (guess.length() == 1) {
                    makeGuess(guess.charAt(0));
                }
            }
        });
        guessField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    guessButton.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private String getSelectedDiff() {
        return selectedDiff;
    }
    private void setSelectedDiff(String setter) {
        selectedDiff = setter;
    }

    private String addSpaces(String str) {
        StringBuilder spacedStr = new StringBuilder();
        for (char c : str.toCharArray()) {
            spacedStr.append(c).append(" ");
        }
        return spacedStr.toString();
    }

    private void makeGuess(char guess) {
        boolean isGuessCorrect = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == guess) {
                currentGuess[i] = guess;
                isGuessCorrect = true;
            }
        }

        if (!isGuessCorrect) {
            numberOfGuesses--;
        }

        if (new String(currentGuess).equals(currentWord)) {
            startGame();
        } else if (numberOfGuesses == 0) {
            startGame();
        }

        updateScreen();
    }
}

