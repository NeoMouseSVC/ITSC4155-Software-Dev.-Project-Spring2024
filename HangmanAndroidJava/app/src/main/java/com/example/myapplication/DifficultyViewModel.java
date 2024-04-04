package com.example.myapplication;

import androidx.lifecycle.ViewModel;

public class DifficultyViewModel extends ViewModel {
    private String selectedDifficulty = "";

    public String getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void setSelectedDifficulty(String difficulty) {
        selectedDifficulty = difficulty;
    }
}
