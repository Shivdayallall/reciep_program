public class Main {
    public static void main(String[] args) {
        Recipe proteinPasta = new Recipe("Protein Pasta", "Dinner", "Boil pasta, Cook the meat, Create the sauce, Mix pasta and Meat with sauce", 45);
        Ingredient cream = new Ingredient("Cream", 1, "Cups");
        Ingredient meat = new Ingredient("Ground Meat", 1, "Pack");
        Ingredient onions = new Ingredient("Onion", 1, "gram");

        proteinPasta.addIngredient(cream);
        proteinPasta.addIngredient(meat);
        proteinPasta.addIngredient(onions);

        proteinPasta.displayRecipe();















    }
}