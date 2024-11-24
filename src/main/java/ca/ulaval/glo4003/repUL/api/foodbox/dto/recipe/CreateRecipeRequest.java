package ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe;

import ca.ulaval.glo4003.repUL.api.foodbox.dto.ingredient.CreateIngredientRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateRecipeRequest(
        @NotEmpty
        String name,
        @NotNull
        int calories,
        @NotEmpty
        List<CreateIngredientRequest> ingredients
) {
}