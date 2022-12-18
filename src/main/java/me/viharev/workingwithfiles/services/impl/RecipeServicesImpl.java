package me.viharev.workingwithfiles.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.viharev.workingwithfiles.models.Recipe;
import me.viharev.workingwithfiles.services.FilesServiceRecipe;
import me.viharev.workingwithfiles.services.RecipeServices;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@Service
public class RecipeServicesImpl implements RecipeServices {
    private FilesServiceRecipe filesServiceRecipe;
    private Integer idRecipe = 0;
    public static Integer counter = 0;
    private static Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();

    public RecipeServicesImpl(FilesServiceRecipe filesServiceRecipe) {
        this.filesServiceRecipe = filesServiceRecipe;
    }
    @PostConstruct
    private void init() {
        readRecipeFromJsonFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        Recipe recipe1 = recipeMap.put(idRecipe, recipe);
        idRecipe = counter++;
        saveRecipeToJsonFile();
        return recipe1;
    }

    @Override
    public void getRecipe() {
        for (Recipe recipe : recipeMap.values()) {
            System.out.println(recipe.toString());
        }
    }

    @Override
    public Recipe getRecipeById(Integer idRecipe) {
        for (Integer integer : recipeMap.keySet()) {
            if (integer.equals(idRecipe)) {
                return recipeMap.get(idRecipe);
            }
        }
        return null;
    }

    @Override
    public Recipe editRecipe(Integer idRecipe, Recipe recipe) {
        for (Integer key : recipeMap.keySet()) {
            if (key.equals(idRecipe)) {
                recipeMap.put(idRecipe, recipe);
                saveRecipeToJsonFile();
                return recipe;
            }
        }
        return null;
    }
    @Override
    public boolean removeRecipe(Integer idRecipe) {
        for (Integer key : recipeMap.keySet()) {
            if (key.equals(idRecipe)) {
                recipeMap.remove(idRecipe);
                saveRecipeToJsonFile();
                return true;
            }
        }
        return false;
    }
    private void saveRecipeToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesServiceRecipe.saveRecipeToJsonFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readRecipeFromJsonFile() {
        try {
            String json = filesServiceRecipe.readRecipeFromJsonFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
