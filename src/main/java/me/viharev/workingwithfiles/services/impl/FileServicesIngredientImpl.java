package me.viharev.workingwithfiles.services.impl;

import me.viharev.workingwithfiles.services.FileServicesIngredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServicesIngredientImpl implements FileServicesIngredient {
    @Value("${path.to.data.ingredient.file}")
    private String pathToFile;
    @Value("${name.of.data.ingredient.name}")
    private String nameOfFile;
    @Override
    public boolean saveIngredientToFile(String json) {
        try {
            cleanIngredientFile();
            Files.writeString(Path.of(pathToFile, nameOfFile), json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String readIngredientFromFile() {
        try {
           return Files.readString(Path.of(pathToFile, nameOfFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean cleanIngredientFile() {
        try {
            Path path = Path.of(pathToFile, nameOfFile);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
