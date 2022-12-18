package me.viharev.workingwithfiles.services;

import me.viharev.workingwithfiles.models.Recipe;

public interface RecipeServices {
    public Recipe addRecipe(Recipe recipe);

    public void getRecipe();

    Recipe getRecipeById(Integer idRecipe);

    Recipe editRecipe(Integer idRecipe, Recipe recipe);

    boolean removeRecipe(Integer idRecipe);
}
