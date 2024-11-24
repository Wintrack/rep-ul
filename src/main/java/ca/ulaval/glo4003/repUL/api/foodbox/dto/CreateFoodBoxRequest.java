package ca.ulaval.glo4003.repUL.api.foodbox.dto;

import ca.ulaval.glo4003.repUL.api.foodbox.dto.recipe.CreateRecipeRequest;
import ca.ulaval.glo4003.repUL.domain.foodbox.type.BoxType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateFoodBoxRequest(
        @NotNull
        BoxType type,
        @NotEmpty
        List<CreateRecipeRequest> recipes
) {
}

