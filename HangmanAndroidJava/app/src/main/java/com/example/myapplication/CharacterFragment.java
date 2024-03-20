package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class CharacterFragment extends Fragment {

    // Add fields for the hangman body parts
    private ImageView head;
    private ImageView body;
    private ImageView leftArm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        // Initialize the hangman body parts
        head = view.findViewById(R.id.imageView9);
        body = view.findViewById(R.id.imageView10);
        leftArm = view.findViewById(R.id.imageView11);

        // Set adjustViewBounds to true for each body part
        head.setAdjustViewBounds(true);
        body.setAdjustViewBounds(true);
        leftArm.setAdjustViewBounds(true);

        LinearLayout itemContainer = view.findViewById(R.id.itemContainer);

        // an array of Drawable resource IDs for the images
        int[] imageResources = {R.drawable.head_customized_1, R.drawable.body_customized_1, R.drawable.arm1_customized_1};

        // an array of the corresponding body parts
        ImageView[] bodyParts = {head, body, leftArm};

        for (int i = 0; i < imageResources.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageResources[i]);

            // Set layout parameters as needed, for example:
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300); // Adjust these values as needed
            imageView.setLayoutParams(layoutParams);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            final ImageView bodyPart = bodyParts[i];
            int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Replace the corresponding body part in the hangman view with the clicked image
                    bodyPart.setImageResource(imageResources[finalI]);
                }
            });

            itemContainer.addView(imageView);
        }

        return view;
    }
}

