package me.viharev.workingwithfiles.services;

public interface FilesServiceRecipe {
    boolean saveRecipeToJsonFile(String json);

    String readRecipeFromJsonFile();
}
