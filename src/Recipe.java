import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Recipe {
    private String recipeName;
    private String category;
    private String instructions;
    private int cookTime;
    private HashMap<String, Ingredient> ingredients;
    private static final int RECIPE_NAME_INDEX = 0;
    private static final int RECIPE_CATEGORY_INDEX = 1;
    private static final int RECIPE_INSTRUCTIONS_INDEX = 2;
    private static final int RECIPE_COOK_TIME_INDEX = 3;
    private static final int RECIPE_INGREDIENTS_INDEX = 4;


    public Recipe(String recipeName, String category, String instructions, int cookTime) {
        this.recipeName = recipeName;
        this.category = category;
        this.instructions = instructions;
        this.cookTime = cookTime;
        this.ingredients = new HashMap<String, Ingredient>();
    }

    public Recipe(String filepath) {
        /*
        New constructor that parses ingredients from file
        */
        this.ingredients = new HashMap<String, Ingredient>();
        try {
            this.parseRecipeFromFile(filepath);
        } catch (Exception e) {
            System.out.println("Error parsing file.");
        }
    }

    private void parseRecipeFromFile(String path){
        try(
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        ) {
            boolean ok = this.buildRecipe(reader);
            if (!ok) {
                System.out.println("Oh no");
            }
        } catch (IOException e) {
            // Return error here
            System.out.println("Error occured with file, possibly missing or misspelled.");
            return;
        }
    }

    private boolean buildRecipe(BufferedReader reader) {
        Stream<String> stream = reader.lines();
        List<String> linesList = stream.toList();
        // Make sure the recipe has exactly 5 lines: Must change if the number of parameters change
        if (linesList.size() != 5) {
            return false;
        }
        // first line is recipe name
        this.recipeName = linesList.get(RECIPE_NAME_INDEX);
        // second line is category
        this.category = linesList.get(RECIPE_CATEGORY_INDEX);
        // third line is instructions
        this.instructions = linesList.get(RECIPE_INSTRUCTIONS_INDEX);
        // fourth line is cook-time in minutes
        String cook_time = linesList.get(RECIPE_COOK_TIME_INDEX);
        // parse to integer
        try {
            this.cookTime = Integer.valueOf(cook_time);
        } catch (NumberFormatException e) {
            return false;
        }
        // 5th line is ingredients
        // requires further parsing
        List<Ingredient> ingredients_list = parseIngredients(linesList.get(RECIPE_INGREDIENTS_INDEX));
        // Add them to list
        for (Ingredient ing: ingredients_list) {
            addIngredient(ing);
        }

        return true;
    }

    private List<Ingredient> parseIngredients(String s_ingredients) {
        List<Ingredient> temp_ingredients = new ArrayList<>();
        // each ingredient is space separated
        // each ingredient var is comma separated
        String[] individual_ingredients_list = s_ingredients.split(" ");
        // Validate that each ingredient has 3 sub strings
        for (String ing: individual_ingredients_list) {
            String[] ingredient_components = ing.split(",");
            if (ingredient_components.length != 3) {
                // This needs to be handled more gracefully
                try {
                    throw new IOException("Encountered error while parsing recipe, invalid ingredients in recipe.");
                } catch (IOException e) {
                    System.out.println(e.getCause() + "@ " + ing);
                }
            } else {
                // Parse int or default, this can be handled more gracefully, but good enough for now
                temp_ingredients.add(new Ingredient(
                        ingredient_components[0],
                        Utility.ParseIntOrDefault(ingredient_components[1]),
                        ingredient_components[2]
                ));
            }
        }

        return temp_ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getCategory() {
        return category;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setCookTime(int cookTime) {
        if(cookTime > 0) {
            this.cookTime = cookTime;
        } else {
            System.out.println("Cook time must be greater than 0");
        }

    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void displayRecipe() {
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Category: " + category);
        System.out.println("Cooking Time: " + cookTime + " minutes");
        System.out.println("Instructions: " + instructions);
        System.out.println("Ingredients:");
        for (Ingredient ingredient : this.ingredients.values()) {
            System.out.println("- " + ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit());
        }
    }

}
