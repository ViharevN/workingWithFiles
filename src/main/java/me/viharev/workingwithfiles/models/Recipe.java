package me.viharev.workingwithfiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@NonNull
public class Recipe {
    private String nameRecipe;
    private Integer timeOfPreparing;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<String> stepList = new ArrayList<>();

}


