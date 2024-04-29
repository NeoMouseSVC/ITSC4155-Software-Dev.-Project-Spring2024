package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordBanks {
    // Define a map to hold the word banks
    private static final Map<String, String[]> wordBanks = new HashMap<>();

    static {

        // "Movies and TV" category
        wordBanks.put("Easy-MTV", new String[]{
                "Spider-Man", "Jurassic Park", "Lion King", "Moana",
                "Frozen", "The Simpsons", "Friends", "Seinfeld","Toy Story",
                "Beauty and the Beast", "Incredibles", "Finding Nemo", "Up",
                "Mickey Mouse Clubhouse", "Sesame Street", "Blue's Clues", "Dora the Explorer", "Paw Patrol"
        });

        wordBanks.put("Medium-MTV", new String[]{
                "The Hobbit", "Star Wars", "Harry Potter",
                "Avengers", "The Office", "Breaking Bad", "Game of Thrones",
                "Stranger Things", "Walking Dead","Indiana Jones", "Jurassic World",
                "Hunger Games", "Lord of the Rings", "Princess Bride", "Modern Family", "Grey's Anatomy"
        });

        wordBanks.put("Hard-MTV", new String[]{
                "Mulholland Drive", "Blade Runner", "Parasite",
                "Crouching Tiger", "Hidden Dragon", "Whiplash", "Mad Men",
                "The Sopranos", "Six Feet Under", "True Detective", "Fargo",
                "The Shining", "Pulp Fiction", "Inception", "Amélie", "Spirited Away",
                "Westworld", "BoJack Horseman", "Arrested Development", "The Good Place"
        });

        // "Foods" category
        wordBanks.put("Easy-Foods", new String[]{
                "bread", "cheese", "chicken", "chocolate", "coffee", "donut", "egg", "fish",
                "ice cream", "juice", "milk", "orange", "pasta", "pizza", "potato", "rice", "sandwich",
                "soup", "spaghetti", "steak", "strawberry", "sushi", "tea", "tomato", "vegetable"
        });

        wordBanks.put("Medium-Foods", new String[]{
                "appetizer", "broccoli", "caffeine", "cereal", "cilantro", "cinnamon", "clam chowder",
                "dessert", "doughnut", "dressing", "entrée", "flavorful", "fork",
                "fruit", "gourmet", "grapes", "gravy", "grill", "hamburger", "herbal", "honey", "horseradish",
                "jam", "kitchen", "lettuce", "lobster", "maple syrup", "mushroom", "mustard", "nutmeg",
                "olive oil", "onion", "pancake", "parmesan cheese", "pastry", "peanut butter", "pepper",
                "pie", "potatoes", "protein", "pumpkin", "raspberry", "restaurant",
                "roast", "salad", "salmon", "sausage", "seafood", "seasoning", "shrimp", "snack", "sour cream",
                "spice", "spinach", "spoon", "steak", "strawberries" ,
                "sugar", "sushi", "syrup", "tea", "tomato sauce", "tortilla", "turkey", "vinegar", "waffle", "yogurt"
        });

        wordBanks.put("Hard-Foods", new String[]{
                "bouillabaisse", "cassoulet", "consomme", "foie gras", "fondue", "guacamole",
                "moussaka", "paella", "ratatouille", "risotto", "sauerbraten",
                "sauerkraut", "schnitzel", "soufflé", "strudel", "tataki", "tempura"
        });

        // "Countries" category
        wordBanks.put("Easy-Countries", new String[]{
                "America", "Canada", "Mexico", "India", "Brazil","China", "England",
                "France", "Germany", "Japan", "Russia", "Spain"
        });

        wordBanks.put("Medium-Countries", new String[]{
                "Argentina", "Australia","Austria", "Belgium", "Colombia", "Denmark", "Egypt", "Greece", "Ireland", "Israel",
                "Netherlands", "New Zealand", "Poland", "Portugal", "South Africa", "Sweden", "Switzerland", "Thailand", "Turkey", "Vietnam"
        });

        wordBanks.put("Hard-Countries", new String[]{
                "Mozambique", "Azerbaijan", "Bhutan", "Cambodia", "Ethiopia", "Fiji",
                "Guyana", "Iceland", "Kazakhstan", "Kyrgyzstan", "Luxembourg","Madagascar", "Malawi",
                "Mongolia", "Montenegro","Nepal", "Nicaragua", "Senegal", "Slovenia", "Tanzania", "Uganda", "Uruguay"
        });

        // "Video Games" category
        wordBanks.put("Easy-Video Games", new String[]{
                "Mario", "Sonic", "Pac-Man", "Zelda", "Pong", "Tetris", "Kirby",
                "Donkey Kong", "Yoshi", "Minecraft", "Link"," Pokemon",
                "Wario", "Luigi", "Waluigi", "Bowser", "Pikachu"
        });

        wordBanks.put("Medium-Video Games", new String[]{
                "Castlevania", "Street Fighter", "Final Fantasy",
                "Halo", "Among Us", "Rocket League", "Grand Theft Auto", "Counter-Strike",
                "Valorant", "Apex Legends", "Clash Royale", "Battlefield", "Destiny"
        });

        wordBanks.put("Hard-Video Games", new String[]{
                 "Superhot", "Xenogears", "Ultima", "Demon's Souls", "Brawlhalla",
                "Assetto Corsa", "Lethal Company", "Brawl Stars", "Dark Souls"

        });
    }

    // Method to get the word bank for a given combination of difficulty and category
    public static String[] getWordBank(String difficulty, String category) {
        if (category.equals("All")) {
            // Collect all word banks for the specified difficulty
            ArrayList<String> combinedWordBank = new ArrayList<>();

            for (Map.Entry<String, String[]> entry : wordBanks.entrySet()) {
                if (entry.getKey().startsWith(difficulty + "-")) {
                    combinedWordBank.addAll(Arrays.asList(entry.getValue()));
                }
            }

            return combinedWordBank.toArray(new String[0]);
        } else {
            // Return the specific word bank for the given combination
            String key = difficulty + "-" + category;
            return wordBanks.getOrDefault(key, new String[]{});
        }
    }
}
