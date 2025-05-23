package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class hang_game extends AppCompatActivity {

    private String currentWord;
    private char[] currentGuess;
    private int numberOfGuesses;
    private String selectedDiff;
    private String selectedCategory;
    private String[] currentWordBank;
    private static final int HANGMAN_HEAD_ID = R.id.hang_head;
    private SharedPreferences sharedPreferences;

    private static final int MAX_INCORRECT_GUESSES = 6;
    private static final int[] HANGMAN_PARTS = {
            R.id.hang_head,
            R.id.hang_body,
            R.id.hang_left_arm,
            R.id.hang_right_arm,
            R.id.hang_left_leg,
            R.id.hang_right_leg
    };

    private int currentIncorrectGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_game);

        int selectedTheme = PreferenceUtils.getThemeButton1(this);
        findViewById(R.id.main).setBackgroundResource(selectedTheme);

        sharedPreferences = getSharedPreferences("HangmanPrefs", Context.MODE_PRIVATE);

        // Retrieve the selected head image resource ID from SharedPreferences
        int selectedHeadImageId = sharedPreferences.getInt("hangman_head_image", R.drawable.head);

        // Update the hangman's head ImageView with the selected image resource
        ImageView hangmanHeadImageView = findViewById(HANGMAN_HEAD_ID);
        hangmanHeadImageView.setImageResource(selectedHeadImageId);


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

        // Reset the number of incorrect guesses at the start of each game
        currentIncorrectGuesses = 0;
        findViewById(R.id.hang_head).setVisibility(View.INVISIBLE);
        findViewById(R.id.hang_body).setVisibility(View.INVISIBLE);
        findViewById(R.id.hang_left_arm).setVisibility(View.INVISIBLE);
        findViewById(R.id.hang_right_arm).setVisibility(View.INVISIBLE);
        findViewById(R.id.hang_left_leg).setVisibility(View.INVISIBLE);
        findViewById(R.id.hang_right_leg).setVisibility(View.INVISIBLE);

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

        // Initialize currentGuess to represent blanks, but retain special characters
        for (int i = 0; i < currentWord.length(); i++) {
            char c = currentWord.charAt(i);
            if (c == ' ' || c == '-' || c == '\'') { // Retain spaces, hyphens, apostrophes
                currentGuess[i] = c; // Keep these characters as they are
            } else {
                currentGuess[i] = '_'; // Use underscores for blanks
            }
        }

        numberOfGuesses = 6;
        updateScreen();
    }

    private void updateScreen() {
        TextView wordTextView = findViewById(R.id.madeit);

        // Construct the displayed word with appropriate spacing between characters
        StringBuilder displayedWord = new StringBuilder();

        for (int i = 0; i < currentGuess.length; i++) {
            char c = currentGuess[i];
            if (c == ' ') { // Represent spaces between words
                displayedWord.append("   ");
            } else {
                displayedWord.append(c).append(" "); // Add space between characters
            }
        }

        String[] words = displayedWord.toString().trim().split(" {3}"); // Split by space markers

        StringBuilder line1 = new StringBuilder(); // First line content
        StringBuilder line2 = new StringBuilder(); // Second line content

        int maxCharsPerLine = 11; // Maximum characters per line
        int currentCharCount = 0; // Track character count for the first line

        // Distribute words to the appropriate line based on character limits
        for (String word : words) {
            if (currentCharCount + word.length() <= maxCharsPerLine) {
                // If adding the word doesn't exceed the limit, add it to line 1
                line1.append(word).append("   "); // Maintain spacing
                currentCharCount += word.length() + 3; // Update character count
            } else {
                // Otherwise, add to line 2
                line2.append(word).append("   ");
            }
        }

        // Build the final text with correct line breaks
        StringBuilder finalDisplay = new StringBuilder();
        finalDisplay.append(line1.toString().trim());

        if (line2.length() > 0) {
            finalDisplay.append("\n").append(line2.toString().trim()); // Add newline for second line
        }

        wordTextView.setText(finalDisplay.toString()); // Set the text with appropriate spacing
        wordTextView.setSingleLine(false); // Allow multiline text
        wordTextView.setMaxLines(2); // Allow up to two lines

        // Reset other UI components
        EditText guessField = findViewById(R.id.guessfeild);
        guessField.setText(""); // Reset guess field
        guessField.requestFocus();

        TextView categoryTextView = findViewById(R.id.category);
        categoryTextView.setText("Category: " + getSelectedCategory());

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
        if (currentWord == null) { // Ensure currentWord is not null
            Log.e("Error", "Current word is null in makeGuess");
            return;
        }

        boolean isGuessCorrect = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (Character.toLowerCase(currentWord.charAt(i)) == Character.toLowerCase(guess)) { // Case-insensitive matching
                currentGuess[i] = currentWord.charAt(i); // Set the correct letter
                isGuessCorrect = true;
            }
        }

        if (!isGuessCorrect) {
            currentIncorrectGuesses++;
            numberOfGuesses--; // Decrease the number of guesses if the guess is incorrect
            if (currentIncorrectGuesses <= MAX_INCORRECT_GUESSES) {
                showHangmanPart(currentIncorrectGuesses - 1);
            }
        }

        if (new String(currentGuess).equals(currentWord)) {
            Toast.makeText(this, "You guessed the word!", Toast.LENGTH_SHORT).show();
            Intent winIntent = new Intent(hang_game.this, win_screen.class);

            // Pass the difficulty and category for reference
            winIntent.putExtra("difficulty", getSelectedDiff());
            winIntent.putExtra("category", getSelectedCategory());
            updateCurrency(getSelectedDiff());
            startActivity(winIntent);
            finish(); // Finish the current activity to avoid multiple instances
        } else if (numberOfGuesses == 0) {
            Toast.makeText(this, "You lost! The word was: " + currentWord, Toast.LENGTH_SHORT).show();
            Intent loseIntent = new Intent(hang_game.this, lose_screen.class);

            // Pass the difficulty and category for reference
            loseIntent.putExtra("difficulty", getSelectedDiff());
            loseIntent.putExtra("category", getSelectedCategory());

            startActivity(loseIntent);
            finish(); // Ensure the current activity is finished
        }

        updateScreen(); // Update the UI after guessing
    }
    private void showHangmanPart(int partIndex) {
        // Ensure that the index is within the bounds of the array
        if (partIndex >= 0 && partIndex < HANGMAN_PARTS.length) {
            ImageView hangmanPart = findViewById(HANGMAN_PARTS[partIndex]);
            hangmanPart.setVisibility(View.VISIBLE);
        }
    }

    private void updateCurrency(String diff) {
        int increment = 0;
        switch (diff.toLowerCase()) {
            case "easy":
                increment = 10;
                break;
            case "medium":
                increment = 15;
                break;
            case "hard":
                increment = 20;
                break;
        }
        MainActivity.currencyCount += increment;
        PreferenceUtils.saveCurrencyCount(this, MainActivity.currencyCount);
    }



}

