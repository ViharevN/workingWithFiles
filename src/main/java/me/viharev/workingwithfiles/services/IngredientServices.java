package me.viharev.workingwithfiles.services;

import me.viharev.workingwithfiles.models.Ingredient;
import org.springframework.http.ResponseEntity;

public interface IngredientServices {
    public Ingredient addIngredient(Ingredient ingredient);

    Ingredient editIngredient(Integer id, Ingredient ingredient);

    boolean removeIngredient(Integer id);

    Ingredient getIngredientById(Integer id);

    Ingredient getAllIngredients();
}
