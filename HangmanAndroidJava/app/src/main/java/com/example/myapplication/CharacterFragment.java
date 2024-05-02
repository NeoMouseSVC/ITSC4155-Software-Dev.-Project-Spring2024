package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class CharacterFragment extends Fragment {

    // Add fields for the hangman body parts
    private ImageView head;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int selectedHeadImageId = sharedPreferences.getInt("selected_head_image", R.drawable.head);

        // Initialize the hangman body parts
        head = view.findViewById(R.id.imageView9);
        head.setImageResource(selectedHeadImageId);

        LinearLayout itemContainer = view.findViewById(R.id.itemContainer);

        // an array of Drawable resource IDs for the images
        int[] imageResources = {R.drawable.head, R.drawable.head_customized_1, R.drawable.head_customized_2, R.drawable.head_customized_3, R.drawable.head_customized_4, R.drawable.head_customized_5, R.drawable.head_customized_6, R.drawable.head_customized_7};

        // an array of the corresponding body parts
        ImageView[] bodyParts = {head, head, head, head, head, head, head, head};

        for (int i = 0; i < imageResources.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageResources[i]);

            // Set layout parameters as needed, for example:
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300); // Adjust these values as needed
            imageView.setLayoutParams(layoutParams);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            final int selectedImageId = imageResources[i];
            final ImageView bodyPart = bodyParts[i];
            int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Replace the corresponding body part in the hangman view with the clicked image
                    bodyPart.setImageResource(imageResources[finalI]);
                    // Save the selected head image resource ID in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_head_image", selectedImageId);
                    editor.apply();
                    // Replace the head image with the selected one
                    head.setImageResource(selectedImageId);
                    updateHangmanHead(selectedImageId);
                }
            });

            itemContainer.addView(imageView);
        }

        return view;
    }

    private void updateHangmanHead(int selectedImageId) {
        // You can send a broadcast, update SharedPreferences, or use any other method to notify hang_game activity about the change.
        // For simplicity, let's assume you update SharedPreferences in hang_game and handle the change there.
        SharedPreferences hangmanSharedPreferences = getActivity().getSharedPreferences("HangmanPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = hangmanSharedPreferences.edit();
        editor.putInt("hangman_head_image", selectedImageId);
        editor.apply();
    }
}






