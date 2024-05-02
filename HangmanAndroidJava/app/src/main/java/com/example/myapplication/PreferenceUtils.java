package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    // Key constants for shared preferences
    private static final String PREF_CURRENCY_COUNT = "currency_count";
    private static final String PREF_THEME_BUTTON_1 = "themeButton1";
    // Add more keys for other preferences as needed

    public static void saveCurrencyCount(Context context, int count) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_CURRENCY_COUNT, count);
        editor.apply();
    }

    public static int getCurrencyCount(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(PREF_CURRENCY_COUNT, 0); // Default value is 0 if not found
    }

    public static void saveThemeButton1(Context context, int count) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_THEME_BUTTON_1, count);
        editor.apply();
    }

    public static int getThemeButton1(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(PREF_THEME_BUTTON_1, 0); // Default value is 0 if not found
    }

    // Add methods for other preferences as needed
}
