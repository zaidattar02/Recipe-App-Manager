package model;

import java.util.ArrayList;

//Represents a class Recipe having a name,list of ingredients,number and
// boolean values:vegan,halal and if it contains nuts.
public class Recipe {
    private String name;
    private boolean isVegan;
    private boolean isHalal;
    private boolean containsNuts;
    private ArrayList<Ingredient> ingredients;
    private int recipeNumber;

    /*
     * REQUIRES: ingredients list is not null and recipe name has a non-zero length
     * and recipeNumber is > 0
     * EFFECTS: name of recipe is set to n, ingredients list is set to ing, isHalal
     * is set to halal, isVegan is set to vegan, containsNuts is set to nuts, and recipe
     * number is set to number.
     */
    public Recipe(String n, boolean halal, boolean vegan, boolean nuts, ArrayList<Ingredient> ing, int number) {
        ingredients = ing;
        name = n;
        isHalal = halal;
        isVegan = vegan;
        containsNuts = nuts;
        recipeNumber = number;
    }

    /*
     * REQUIRES: Ingredients list is not null
     * MODIFIES: this
     * EFFECTS: returns a list of Ingredients in type String
     */
    public ArrayList<String> getIngredientsString() {
        ArrayList<String> ingNames = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingNames.add(i.getIngredientName());
        }
        return ingNames;
    }

    /*
     * REQUIRES: Ingredients and Recipe name is not null
     * EFFECTS: returns recipe's name and ingredients of each recipe
     */
    public void printRecipe() {
        System.out.println("Recipe: " + " " + getRecipeName());
        System.out.println("Ingredients: ");
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println(ingredients.get(i).getIngredientName());
        }
    }

    /*
     *REQUIRES: Ingredients and Recipe name is not null
     * MODIFIES: this
     * EFFECTS: returns a list of ingredients after removing ingredient passed in as String.
     */
    public void removeIngredient(String ingName) {
        int trgtIdx = -1;
        int ingIdx = 0;
        for (Ingredient i : ingredients) {
            if (i.getIngredientName().equals(ingName)) {
                trgtIdx = ingIdx;
                //removed = i;
                break;
            }
            ingIdx++;
        }
        if (trgtIdx == -1) {
            System.out.println("Could not find the ingredient. Nothing removed");
        } else {
            ingredients.remove(trgtIdx);
        }
    }

    public String getRecipeName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean isRecipeHalal() {
        return isHalal;
    }

    public boolean isRecipeVegan() {
        return isVegan;
    }

    public boolean doesRecipeContainNuts() {
        return containsNuts;
    }

    public int getRecipeNumber() {
        return recipeNumber;
    }

}