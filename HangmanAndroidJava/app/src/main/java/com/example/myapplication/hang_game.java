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

    private final String[] Beginnerwords = {
            "apple", "banana", "cherry", "elderberry",
            "fig", "grape", "honeydew", "iceberg lettuce", "jalapeno",
            "kiwi", "lemon", "mango", "nectarine", "orange",
            "pineapple", "quinoa", "raspberry", "strawberry", "tomato",
            "ugli fruit", "vanilla bean", "watermelon", "yellow pepper",
            "zucchini", "yam", "wheat", "vinegar",
            "tea", "squash", "radish", "quail", "pumpkin"
    };

    private final String[] Intermediatewords = {
            "abundance", "bamboo", "caramel", "dandelion",
            "eccentric", "flabbergasted", "gallivant", "haphazard", "indomitable",
            "juxtaposition", "kaleidoscope", "labyrinth", "magnanimous", "nefarious",
            "oblivion", "paradox", "quintessential", "recalcitrant", "serendipity",
            "trepidation", "ubiquitous", "vagabond", "whimsical", "xenophobia",
            "yesterday", "zephyr"
    };

    private final String[] Advancedwords = {
            "antidisestablishmentarianism", "bewilderment", "capriciousness", "discombobulate",
            "equanimity", "facetious", "garrulous", "horticulture", "idiosyncrasy",
            "juxtapose", "kafkaesque", "lackadaisical", "mnemonic", "onomatopoeia",
            "perspicacious", "quintessence", "recumbentibus", "sesquipedalian", "tenebrous",
            "unabashed", "vociferous", "winsome", "xenoglossia", "yestreen",
            "zeitgeist"
    };


    private String currentWord;
    private char[] currentGuess;
    private int numberOfGuesses;
    private String selectedDiff;
    private String selectedCategory;

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
            // Category selection in mainActivity hasn't been implemented --> setSelectedCategory(i.getStringExtra("category"));
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
        String currentWord;
        String category = usercategory;
        switch (diff.toLowerCase()) {
            case "easy":
                currentWord = getRandomWordBeginner(category);
                break;
            case "medium":
                currentWord = getRandomWordIntermediate(category);
                break;
            case "hard":
                currentWord = getRandomWordAdvanced(category);
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level");
        }

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

    // FUNCTION TO GET RANDOM WORD, STILL WORKING ON HOW USER SELECTS CATEGORY
    private String getRandomWordBeginner(String category) {
        String currCategory = category;
        Random random = new Random();
        currentWord = Beginnerwords[random.nextInt(Beginnerwords.length)];
        return currentWord;
    }
    private String getRandomWordIntermediate(String category) {
        String currCategory = category;
        Random random = new Random();
        currentWord = Intermediatewords[random.nextInt(Intermediatewords.length)];
        return currentWord;
    }
    private String getRandomWordAdvanced(String category) {
        String currCategory = category;
        Random random = new Random();
        currentWord = Advancedwords[random.nextInt(Advancedwords.length)];
        return currentWord;
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

