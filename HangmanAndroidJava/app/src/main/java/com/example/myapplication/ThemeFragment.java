package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class ThemeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        LinearLayout colorContainer = view.findViewById(R.id.colorContainer);

        // an array of colors for the buttons
        int[] colors = {Color.parseColor("#FFC0CB"), Color.parseColor("#ADD8E6"), Color.parseColor("#90EE90")}; // Light red, light blue, light green

        // an array of color names
        String[] colorNames = {"Light Red", "Light Blue", "Light Green"};

        for (int i = 0; i < colors.length; i++) {
            Button button = new Button(getContext());
            button.setBackgroundColor(colors[i]);
            button.setText(colorNames[i]);
            button.setTextSize(24); // Adjust this value as needed

            // Set layout parameters as needed, for example:
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300); // Adjust these values as needed
            button.setLayoutParams(layoutParams);

            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Change the background color of the app
                    view.getRootView().setBackgroundColor(colors[finalI]);
                }
            });

            colorContainer.addView(button);
        }

        return view;
    }
}

