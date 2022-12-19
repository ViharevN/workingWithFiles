package me.viharev.workingwithfiles.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.viharev.workingwithfiles.models.Recipe;
import me.viharev.workingwithfiles.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@AllArgsConstructor
@Data
@Tag(
        name="Рецепт контроллер",
        description = "CRUD-операции с рецептами")
public class RecipeController {
    private RecipeServices recipeServices;

    @PostMapping("/add")
    @Operation(
            summary = "Добавление рецепта",
            description = "добавляем рецепт через Post")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "рецепт добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema=@Schema(implementation = Recipe.class))
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
       recipeServices.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/get/{id}")
    @Operation(
            summary = "получаем рецепт",
            description = "получаем рецепт по его id через Get"
    )
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeServices.getRecipeById(id));
    }

    @PutMapping("/edit/{id}")
    @Operation(
            summary = "изменение рецепта",
            description = "изменяем рецепт по его id"
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        this.recipeServices.editRecipe(id, recipe);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "удаление рецепта",
            description = "удаление рецепта по его id"
    )
    public boolean deleteRecipe(@PathVariable Integer id) {
        if (this.recipeServices.removeRecipe(id)) {
            return true;
        }
        return false;
    }

}





