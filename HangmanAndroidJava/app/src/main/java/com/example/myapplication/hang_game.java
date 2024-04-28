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

    private String currentWord;
    private char[] currentGuess;
    private int numberOfGuesses;
    private String selectedDiff;
    private String selectedCategory;
    private String[] currentWordBank;

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
        //Difficulty modifier - VARIABLE `selected` contains difficulty string
        Intent i = getIntent();
        if(i.getStringExtra("difficulty") != null && i.getStringExtra("category") != null){
            String selected = i.getStringExtra("difficulty");
            setSelectedDiff(i.getStringExtra("difficulty"));
            setSelectedCategory(i.getStringExtra("category"));

            Log.i("Difficulty in-game",getSelectedDiff());
            Log.i("Category in-game",getSelectedCategory());
            TextView difficultyText = findViewById(R.id.difficulty);
            difficultyText.setText(getString(R.string.difficulty_text,selected));
            //Toast.makeText(hang_game.this, "Difficulty : " + getSelectedDiff(), Toast.LENGTH_SHORT).show();

        }else{
            Log.i("Difficulty Failed","Difficulty or Category == null");
        }
        startGame(getSelectedDiff(), getSelectedCategory());

    }



    private void startGame(String diff, String usercategory) {

        String category = usercategory;
        // Retrieve the word bank for the selected difficulty and category
        Log.i("Word bank searching",getSelectedCategory());
        Log.i("Word bank searching",getSelectedDiff());
        currentWordBank = WordBanks.getWordBank(diff, category);

        // Check if the word bank is empty
        if (currentWordBank.length == 0) {
            // Handle the case when the word bank is empty (e.g., no words available)
            Log.e("Error", "Word bank is empty!");
            return;
        }
        Log.i("Word bank length", String.valueOf(currentWordBank.length));
        // Choose a random word from the word bank
        Random random = new Random();
        currentWord = currentWordBank[random.nextInt(currentWordBank.length)];

        Log.i("Word selected",currentWord);

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

        TextView categoryTextView = findViewById(R.id.category);
        categoryTextView.setText("Category:  " + getSelectedCategory());

        TextView chancesTextView = findViewById(R.id.chances_counter);
        chancesTextView.setText("Chances:  " + numberOfGuesses);

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

    private String getSelectedCategory() {
        return selectedCategory;
    }
    private void setSelectedCategory(String inputCategory) {
        selectedCategory = inputCategory;
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
            startGame(getSelectedDiff(),getSelectedCategory());
        } else if (numberOfGuesses == 0) {
            startGame(getSelectedDiff(),getSelectedCategory());
        }

        updateScreen();
    }
}

