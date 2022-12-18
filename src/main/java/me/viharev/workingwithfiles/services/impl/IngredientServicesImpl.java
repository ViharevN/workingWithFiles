package me.viharev.workingwithfiles.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.viharev.workingwithfiles.models.Ingredient;
import me.viharev.workingwithfiles.services.FileServicesIngredient;
import me.viharev.workingwithfiles.services.IngredientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class IngredientServicesImpl implements IngredientServices {
    private FileServicesIngredient fileServicesIngredient;
    public static Integer id = 0;
    private Map<Integer, Ingredient> ingredientMap = new LinkedHashMap<>();

    public IngredientServicesImpl(FileServicesIngredient fileServicesIngredient) {
        this.fileServicesIngredient = fileServicesIngredient;
    }
    @PostConstruct
    public void init() {
        readIngredientFromJsonFile();
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        id++;
        saveIngredientToJsonFile();
        return ingredient;
    }
    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {
        for (Integer key : ingredientMap.keySet()) {
            if (key.equals(id)) {
                ingredientMap.put(id, ingredient);
                saveIngredientToJsonFile();
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public boolean removeIngredient(Integer id) {
        for (Integer key : ingredientMap.keySet()) {
            if (key.equals(id)) {
                ingredientMap.remove(id);
                saveIngredientToJsonFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        for (Integer key : ingredientMap.keySet()) {
            if (key.equals(id)) {
                return ingredientMap.get(id);
            }
        }
        return null;
    }

    @Override
    public Ingredient getAllIngredients() {
        Iterator<Ingredient> iterator = ingredientMap.values().iterator();
        return iterator.next();
    }
    private void saveIngredientToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileServicesIngredient.saveIngredientToFile(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void readIngredientFromJsonFile() {
        String json = fileServicesIngredient.readIngredientFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
