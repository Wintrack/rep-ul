package ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe;

import ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient.IngredientFoundResponse;

import java.util.List;

public record RecipeFoundResponse(
        String name,
        int calories,
        List<IngredientFoundResponse> ingredients
) {
}