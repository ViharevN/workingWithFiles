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
import me.viharev.workingwithfiles.models.Ingredient;
import me.viharev.workingwithfiles.services.IngredientServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
@Data
@Tag(name = "Ингредиент контроллер", description = "CRUD-операции с ингредиентами")
public class IngredientController {
    private IngredientServices ingredientServices;

    @GetMapping("/get/{id}")
    @Operation(
            summary = "получаем ингредиент",
            description = "получаем ингредиент по его id"
    )
    public Ingredient getIngredientById(@PathVariable Integer id) {
        return this.ingredientServices.getIngredientById(id);
    }

    @PostMapping("/ingredient/add")
    @Operation(
            summary = "метод добавления ингредиента",
            description = "добавляем через Post запрос"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент добавлен",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = Ingredient.class
                                                    )
                                            )
                                    )
                            }
                    )
            }
    )
    public Ingredient addIngredientInMap(@RequestBody Ingredient ingredient) {
        this.ingredientServices.addIngredient(ingredient);
        return ingredient;
    }

    @PutMapping("/ingredient/edit/{id}")
    @Operation(
            summary = "метод изменения ингредиента",
            description = "изменяем ингредиент через Put метод по его id"
    )
    public Ingredient editIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        this.ingredientServices.editIngredient(id, ingredient);
        return ingredient;
    }

    @DeleteMapping("/ingredient/delete/{id}")
    @Operation(
            summary = "удаление ингредиента",
            description = "удаляем ингредиент по его id"
    )
    public boolean deleteIngredient(@PathVariable Integer id) {
        if (this.ingredientServices.removeIngredient(id)) {
            return true;
        }
        return false;
    }

    @GetMapping("/get/all")
    @Operation(
            summary = "получаем ингредиенты",
            description = "получаем все ингредиенты"
    )
    public Ingredient getAllIngredients() {
        return this.ingredientServices.getAllIngredients();
    }
}
