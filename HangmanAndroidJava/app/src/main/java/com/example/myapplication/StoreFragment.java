package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StoreFragment extends Fragment {


    Button button1;
    Button button2;
    TextView textView;
    Fragment themeFragment = new ThemeFragment();
    ImageButton themeButton1;
    ImageButton themeButton2;
    ImageButton themeButton3;
    ImageButton themeButton4;
    ImageButton themeButton5;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        // Find ImageButton views for themes
        themeButton1 = view.findViewById(R.id.themeButton1);
        themeButton2 = view.findViewById(R.id.themeButton2);
        themeButton3 = view.findViewById(R.id.themeButton3);
        themeButton4 = view.findViewById(R.id.themeButton4);
        themeButton5 = view.findViewById(R.id.themeButton5);
        button1 = view.findViewById(R.id.Button1);
        button2 = view.findViewById(R.id.Button2);
        textView = view.findViewById(R.id.textView1);

        // Add OnClickListener for each theme button
        themeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user has enough currency to buy the theme

                if (MainActivity.currencyCount >= 20) {
                    // Show purchase confirmation pop-up
                    showPurchaseConfirmation(v);
                } else {
                    // Show message indicating insufficient funds
                    Toast.makeText(getContext(), "Insufficient funds, costs $20", Toast.LENGTH_SHORT).show();
                }
            }
        });

        themeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user has enough currency to buy the theme
                if (MainActivity.currencyCount >= 20) {
                    // Show purchase confirmation pop-up
                    showPurchaseConfirmation(v);
                } else {
                    // Show message indicating insufficient funds
                    Toast.makeText(getContext(), "Insufficient funds, costs $20", Toast.LENGTH_SHORT).show();
                }
            }
        });
        themeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user has enough currency to buy the theme
                if (MainActivity.currencyCount >= 20) {
                    // Show purchase confirmation pop-up
                    showPurchaseConfirmation(v);
                } else {
                    // Show message indicating insufficient funds
                    Toast.makeText(getContext(), "Insufficient funds, costs $20", Toast.LENGTH_SHORT).show();
                }
            }
        });

        themeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user has enough currency to buy the theme
                if (MainActivity.currencyCount >= 20) {
                    // Show purchase confirmation pop-up
                    showPurchaseConfirmation(v);
                } else {
                    // Show message indicating insufficient funds
                    Toast.makeText(getContext(), "Insufficient funds, costs $20", Toast.LENGTH_SHORT).show();
                }
            }
        });

        themeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if user has enough currency to buy the theme
                if (MainActivity.currencyCount >= 20) {
                    // Show purchase confirmation pop-up
                    showPurchaseConfirmation(v);
                } else {
                    // Show message indicating insufficient funds
                    Toast.makeText(getContext(), "Insufficient funds, costs $20", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Repeat for other theme buttons...

        return view;
    }

    // Method to show purchase confirmation dialog
    private void showPurchaseConfirmation(View i) {

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deduct currency count by 20
                MainActivity.currencyCount -= 20;
                // Update currency count in SharedPreferences
                PreferenceUtils.saveCurrencyCount(getContext(), MainActivity.currencyCount);
                i.setVisibility(View.GONE);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);

                if(v.getId() == themeButton1.getId()){
                    customize_screen.themeButton1 += 1;
                    PreferenceUtils.saveThemeButton1(getContext(), customize_screen.themeButton1);
                }
                if(v.getId() == themeButton2.getId()){
                    customize_screen.themeButton2 = 1;
                    PreferenceUtils.saveThemeButton1(getContext(), customize_screen.themeButton2);
                }
                if(v.getId() == themeButton3.getId()){
                    customize_screen.themeButton3 = 1;
                }
                if(v.getId() == themeButton4.getId()){
                    customize_screen.themeButton4 = 1;
                }
                if(v.getId() == themeButton5.getId()){
                    customize_screen.themeButton5 = 1;
                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
            }
        });

    }


}

