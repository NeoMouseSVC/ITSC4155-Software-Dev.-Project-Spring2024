package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class ThemeFragment extends Fragment {

    public static LinearLayout colorContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        colorContainer = view.findViewById(R.id.colorContainer);

        // Retrieve the selected background drawable ID from SharedPreferences
        int backgroundDrawableId = PreferenceUtils.getThemeButton1(requireContext());
        updateBackground(backgroundDrawableId);

        ImageButton themeButton1 = view.findViewById(R.id.themeButton1);
        ImageButton themeButton2 = view.findViewById(R.id.themeButton2);
        ImageButton themeButton3 = view.findViewById(R.id.themeButton3);
        ImageButton themeButton4 = view.findViewById(R.id.themeButton4);
        ImageButton themeButton5 = view.findViewById(R.id.themeButton5);
        ImageButton themeButton6 = view.findViewById(R.id.themeButton6);

        themeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.retro_background_1);
            }
        });

        themeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.retro_background_2);
            }
        });

        themeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.retro_background_3);
            }
        });

        themeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.retro_background_4);
            }
        });

        themeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.retro_background_5);
            }
        });
        themeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBackground(R.drawable.space_background);
            }
        });


        return view;
    }

    private void updateBackground(int drawableId) {
        if (getActivity() instanceof customize_screen) {
            ((customize_screen) getActivity()).updateBackground(drawableId);
        }

    }
}


