package me.viharev.workingwithfiles.services;

public interface FileServicesIngredient {
    boolean saveIngredientToFile(String json);

    String readIngredientFromFile();
}
