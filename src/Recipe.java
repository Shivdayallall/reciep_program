import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {
    private String recipeName;
    private String category;
    private String instructions;
    private int cookTime;
    private List<Ingredient> ingredients;


    public Recipe(String recipeName, String category, String instructions, int cookTime) {
        this.recipeName = recipeName;
        this.category = category;
        this.instructions = instructions;
        this.cookTime = cookTime;
        this.ingredients = new ArrayList<>();
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

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
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
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit());
        }
    }

}
