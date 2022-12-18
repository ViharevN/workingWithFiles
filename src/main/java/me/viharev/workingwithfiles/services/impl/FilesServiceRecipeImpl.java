package me.viharev.workingwithfiles.services.impl;

import me.viharev.workingwithfiles.services.FilesServiceRecipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceRecipeImpl implements FilesServiceRecipe {
    @Value("${path.to.data.recipe.file}")
    private String pathToFile;
    @Value("${name.of.data.recipe.file}")
    private String nameToFile;
    @Override
    public boolean saveRecipeToJsonFile(String json) {
        try {
            cleanRecipeJsonFile();
            Files.writeString(Path.of(pathToFile, nameToFile), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readRecipeFromJsonFile() {
        try {
           return Files.readString(Path.of(pathToFile, nameToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean cleanRecipeJsonFile() {
        try {
            Path path = Path.of(pathToFile, nameToFile);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
