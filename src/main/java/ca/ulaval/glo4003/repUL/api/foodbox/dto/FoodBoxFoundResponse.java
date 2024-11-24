package ca.ulaval.glo4003.repUL.api.foodbox.dto;

import ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe.RecipeFoundResponse;
import ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType;

import java.util.List;

public record FoodBoxFoundResponse(
        String id,
        BoxType type,
        List<RecipeFoundResponse> recipes
) {
}
