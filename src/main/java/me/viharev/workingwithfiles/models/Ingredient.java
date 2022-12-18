package me.viharev.workingwithfiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NonNull
public class Ingredient {
    private String nameIngredient;
    private Integer quantityOfIngredients;
    private String unit;
}
